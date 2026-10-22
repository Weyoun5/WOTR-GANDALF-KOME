import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

/** Exercise the patched native responder, not a duplicate implementation. */
public class KomeSecureSaltTest {
    static Game game;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void set(Object object,String name,Object value)throws Exception{Field f=object.getClass().getDeclaredField(name);f.setAccessible(true);f.set(object,value);}
    static boolean pending()throws Exception{Field f=aV.class.getDeclaredField("c");f.setAccessible(true);return f.getBoolean(game.Q);}
    static bb confirm(String salt,String roll,String committed,String local,int count,int sides)throws Exception{
        check(game.Q.a(count,sides,"roll D6 dice",committed),"Request accepted");set(game.Q,"h",local);
        bb result=game.Q.a(salt,roll);check(!pending(),"Confirmation clears responder state");check(!KomeRollUi.pending(game.Q),"Confirmation closes pending UI");return result;
    }
    public static void main(String[] args)throws Exception{try{
        SwingUtilities.invokeAndWait(()->{game=new Game();game.A.b=true;game.z.a=true;
            new javax.swing.Timer(40,e->{for(Window w:Window.getWindows())if(w instanceof JDialog&&((JDialog)w).isModal())w.dispose();}).start();});
        String salt=Game.F();check(salt.length()==32,"Generated salt length");
        // Same input bytes, so even replacing SHA-1 would not fix the missing boundary.
        check(Game.f(salt+"12").equals(Game.f((salt+"1")+"2")),"Ambiguous preimage demonstrates actual bug");
        check(confirm(salt+"1","2",Game.f(salt+"12"),"3",1,20)==null,"33-character boundary-shifting attack rejected");
        bb good=confirm(salt,"12",Game.f(salt+"12"),"3",1,20);check(good!=null&&good.b()[0]==15,"Correct boundary with same commitment accepted");
        for(String bad:new String[]{null,"",salt.substring(1),salt+"a",salt+salt})
            check(confirm(bad,"2",Game.f(String.valueOf(bad)+"2"),"3",1,6)==null,"Invalid salt rejected even when hash matches: "+(bad==null?"null":bad.length()));
        check(confirm(salt,"2",Game.f(salt+"3"),"3",1,6)==null,"Changed roll rejected");
        check(confirm(salt,"2",Game.f(Game.F()+"2"),"3",1,6)==null,"Changed 32-character salt rejected");
        for(int sides:new int[]{6,20,37,100})for(int count:new int[]{1,2,10,30}){
            StringBuilder remote=new StringBuilder(),local=new StringBuilder();int[] expected=new int[count];
            for(int n=0;n<count;n++){int a=1+n%sides,b=sides-n%sides;if(n>0){remote.append(' ');local.append(' ');}remote.append(a);local.append(b);expected[n]=(a+b)%sides;if(expected[n]==0)expected[n]=sides;}
            String nonce=Game.F();bb result=confirm(nonce,remote.toString(),Game.f(nonce+remote),local.toString(),count,sides);
            check(result!=null&&result.c()==count&&result.d()==sides&&Arrays.equals(expected,result.b()),"Native roll arithmetic/shape preserved");
            check(game.Q.a(nonce,remote.toString())==null,"Duplicate reveal cannot apply twice");
        }
        check(confirm(salt,"1 2",Game.f(salt+"1 2"),"1",2,6)==null,"Mismatched contributions rejected");
        check(confirm(salt,"6",Game.f(salt+"6"),"6",1,6).b()[0]==6,"Recovery and modulo boundary");
        SwingUtilities.invokeAndWait(()->{});
        System.out.println("SECURE SALT PASS: real native responder, concatenation-boundary exploit, null/0/31/33/64-character salts, altered commitments, duplicates, multiple counts/sides, failure recovery");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
