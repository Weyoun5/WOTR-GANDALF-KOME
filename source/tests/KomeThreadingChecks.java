import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/** Deterministic regression checks for the repaired ordering and thread boundaries. */
public class KomeThreadingChecks {
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static boolean pending(Object roller)throws Exception{Field f=roller.getClass().getDeclaredField("c");f.setAccessible(true);return f.getBoolean(roller);}
    public static void main(String[] args)throws Exception{try{
        Game[] holder={null};SwingUtilities.invokeAndWait(()->holder[0]=new Game());Game game=holder[0];
        game.z.a=true;
        CountDownLatch entered=new CountDownLatch(1),release=new CountDownLatch(1);
        SwingUtilities.invokeLater(()->{entered.countDown();try{release.await(5,TimeUnit.SECONDS);}catch(InterruptedException e){throw new RuntimeException(e);}});
        check(entered.await(2,TimeUnit.SECONDS),"EDT gate installed");
        // Finish before the show callback can run: completion must not wait for EDT.
        game.P.a(1,6,"roll D6 dice");check(pending(game.P),"Initiator pending");
        bb result=game.P.a("2");check(result!=null&&!pending(game.P),"Original secure arithmetic returns a result");
        check(!KomeRollUi.pending(game.P),"Finished roll cannot show later");
        String salt=Game.F();check(salt.length()==32,"Native salt has 32 characters");String commitment=Game.f(salt+"1");
        check(game.Q.a(1,6,"roll D6 dice",commitment),"Responder accepts first request");
        check(!game.Q.a(1,6,"roll D6 dice",Game.f("other1")),"Responder rejects overlap");
        check(pending(game.Q),"Overlap preserves original pending state");
        check(game.Q.a(salt,"1")!=null&&!pending(game.Q),"Original confirmation remains valid");
        release.countDown();SwingUtilities.invokeAndWait(()->{});
        check(game.P.a==null||!game.P.a.isShowing(),"No stale initiating modal");
        SwingUtilities.invokeAndWait(()->{for(Window w:Window.getWindows())if(w instanceof JDialog&&((JDialog)w).isModal())check(!w.isVisible(),"No completed-roll modal remains visible");});
        // The real typing listener must return even while another thread holds
        // the command queue monitor. No text is submitted.
        CountDownLatch locked=new CountDownLatch(1),unlock=new CountDownLatch(1);
        Thread lockOwner=new Thread(()->{synchronized(game.z.c){locked.countDown();try{unlock.await(4,TimeUnit.SECONDS);}catch(InterruptedException ignored){}}});lockOwner.start();
        check(locked.await(1,TimeUnit.SECONDS),"Queue gate installed");
        CountDownLatch typed=new CountDownLatch(1);
        SwingUtilities.invokeLater(()->{
            KeyEvent event=new KeyEvent(game.B.q.c,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,KeyEvent.VK_UNDEFINED,'a');
            for(KeyListener listener:game.B.q.c.getKeyListeners())listener.keyTyped(event);
            typed.countDown();
        });
        boolean returned=typed.await(1,TimeUnit.SECONDS);unlock.countDown();lockOwner.join();check(returned,"Typing does not acquire outgoing queue lock");
        Field paneField=m.class.getDeclaredField("i");paneField.setAccessible(true);JTextPane pane=(JTextPane)paneField.get(game.B.q);
        final boolean[] offThread={false};final int[] changes={0};
        pane.getDocument().addDocumentListener(new DocumentListener(){public void insertUpdate(DocumentEvent e){record();}public void removeUpdate(DocumentEvent e){record();}public void changedUpdate(DocumentEvent e){record();}void record(){changes[0]++;if(!SwingUtilities.isEventDispatchThread())offThread[0]=true;}});
        game.B.q.b("Threading regression message");SwingUtilities.invokeAndWait(()->{});
        check(changes[0]>0&&!offThread[0],"Worker message mutations occur on EDT");
        CountDownLatch muteGate=new CountDownLatch(1),muteRelease=new CountDownLatch(1);
        SwingUtilities.invokeLater(()->{muteGate.countDown();try{muteRelease.await(3,TimeUnit.SECONDS);}catch(InterruptedException ignored){}});
        check(muteGate.await(1,TimeUnit.SECONDS),"Replay display gate installed");
        game.B.q.e=true;game.B.q.b("MUTED-REPLAY-REGRESSION");
        game.B.q.e=false;game.B.q.b("VISIBLE-REPLAY-REGRESSION");game.B.q.e=true;
        muteRelease.countDown();SwingUtilities.invokeAndWait(()->{});
        String display=pane.getDocument().getText(0,pane.getDocument().getLength());
        check(!display.contains("MUTED-REPLAY-REGRESSION")&&display.contains("VISIBLE-REPLAY-REGRESSION"),"Queued messages preserve the replay mute state at emission");
        game.B.q.e=false;
        game.P.a(1,6,"roll D6 dice");KomeTransport.disconnect(game.z,game,true);
        check(!pending(game.P)&&!pending(game.Q)&&!game.z.a,"Disconnect cancels pending roll state");
        SwingUtilities.invokeAndWait(()->{});
        System.out.println("THREADING PASS (finish-before-show, duplicate request, native confirmation, nonblocking typing, EDT messages, disconnect cancellation)");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
