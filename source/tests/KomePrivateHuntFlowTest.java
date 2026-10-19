import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class KomePrivateHuntFlowTest {
    static Game game;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void edt(Runnable action)throws Exception{SwingUtilities.invokeAndWait(action);}
    static void waitDraw()throws Exception{long deadline=System.nanoTime()+30000000000L;while(KomePrivateHunt.busy(game)||KomeRollUi.pending(game.P)||KomeRollUi.pending(game.Q)){Thread.sleep(30);if(System.nanoTime()>deadline)throw new AssertionError("Private draw timeout: "+KomeSupport.state(game).message);}edt(()->{});}
    public static void main(String[] args)throws Exception{try{
        edt(()->{game=new Game();game.B.r.b("private-hunt-test");KomeSupport.send(game,"corruptionsettings 0 0");});
        edt(()->KomeSecureCorruption.manual(game,1,3));waitDraw();
        KomeSupport.State state=KomeSupport.state(game);check(state.drawn[1].h()==3,"Three manual tiles drawn: "+state.message);check(KomePrivateHunt.active(game),"Private pool activated");
        final aq[] chosen={null};for(Object item:state.drawn[1].a)if(((aq)item).b().matches("[0-3]r?")){chosen[0]=(aq)item;break;}
        // Repeat the draw if random selection happened to contain only public tiles.
        if(chosen[0]==null){edt(()->KomeSupport.send(game,"return 1"));edt(()->KomeSecureCorruption.manual(game,1,3));waitDraw();for(Object item:state.drawn[1].a)if(((aq)item).b().matches("[0-3]r?")){chosen[0]=(aq)item;break;}}
        check(chosen[0]!=null,"At least one private numerical tile");String actual=chosen[0].b();check(actual.matches("[0-3]r?"),"Shadow sees numerical face");
        edt(()->KomeSupport.send(game,"keep 1 "+chosen[0].n()));String beforeHide=KomeSupport.snapshot(game);
        edt(()->game.r());check(chosen[0].b().equals("Hidden corruption tile"),"Unview hides actual object identity");check(KomePrivateHunt.identity(KomePrivateHunt.state(game),KomePrivateHunt.state(game).tiles.get(Integer.parseInt(chosen[0].n())))==-1,"No private identity without password");check(beforeHide.equals(KomeSupport.snapshot(game)),"Private/public snapshots match");check(KomePrivateHunt.poolImages(game)!=null,"Ghost inventory rendered from public multiset");
        edt(()->{check(game.h("private-hunt-test"),"Shadow password reopens private key");game.H();game.J();});check(actual.equals(chosen[0].b()),"Password reopens correct tile");
        edt(()->KomeSupport.send(game,"tile 1 "+chosen[0].n()+" discard"));check(chosen[0].m()==Game.p[183],"Discard goes to USED pool");
        edt(()->{game.r();KomePrivateHunt.huntAction(game,new java.awt.event.ActionEvent(game,0,"hunt"));});waitDraw();check(Game.p[154].h()+Game.p[153].h()>0,"Ordinary Hunt draw publicly revealed while hand hidden");
        int remaining=Game.p[182].h();
        edt(()->{KomePrivateHunt.State privateState=KomePrivateHunt.state(game);privateState.secret=null;privateState.unlockedPassword=null;KomePrivateHunt.huntAction(game,new java.awt.event.ActionEvent(game,0,"hunt"));check(privateState.pending!=null,"Locked restored seat waits for the same verified draw");check(game.h("private-hunt-test"),"Password resumes pending public Hunt proof");});waitDraw();check(Game.p[182].h()==remaining-1,"Unlock resumes the committed draw exactly once");
        Path file=Paths.get("private-hunt-flow.wotr");edt(()->game.A.c(file.toFile()));String expected=KomeSupport.snapshot(game);
        edt(()->{try{KomeLogReplay.Session replay=KomeLogReplay.open(game,file.toFile());while(!replay.finished)replay.step();game=replay.game;if(!expected.equals(KomeSupport.snapshot(game))){Files.write(Paths.get("private-expected.txt"),expected.getBytes("UTF-8"));Files.write(Paths.get("private-actual.txt"),KomeSupport.snapshot(game).getBytes("UTF-8"));System.out.println("REPLAY STATE: "+KomeSupport.state(game).message);}check(expected.equals(KomeSupport.snapshot(game)),"Private Hunt replay matches");}catch(java.io.IOException error){throw new RuntimeException(error);}});
        System.out.println("PRIVATE HUNT FLOW PASS: shuffle/draw, private object identity, hide/password re-view, used-pool discard, ordinary Hunt reveal, replay.");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
