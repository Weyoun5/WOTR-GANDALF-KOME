import javax.swing.*;
import java.awt.event.*;

public class KomeBaseIsolationTest {
    public static void main(String[] args){try{SwingUtilities.invokeAndWait(()->{
        Game game=new Game();if(KomeSupport.state(game).prepared)throw new AssertionError("Base test selected Kings");
        String context="draw HUNT tile";if(KomePrivateHunt.rollContext(game,context)!=context)throw new AssertionError("Base roll context changed");
        if(KomePrivateHunt.huntAction(game,new ActionEvent(game,0,"hunt")))throw new AssertionError("Base Hunt intercepted");
        aq tile=(aq)Game.p[182].a.get(0);String name=tile.b();java.awt.Image face=tile.c();tile.a(Game.p[183]);
        if(tile.m()!=Game.p[183]||!name.equals(tile.b())||face!=tile.c())throw new AssertionError("Base tile behavior changed");
        if(KomePrivateHunt.active(game)||KomePrivateHunt.STATES.containsKey(game))throw new AssertionError("Base private pool initialized");
        game.B.r.actionPerformed(new ActionEvent(game,0,"hunt"));if(Game.p[153].h()+Game.p[154].h()!=1)throw new AssertionError("Base native Hunt draw failed");
        System.out.println("BASE ISOLATION PASS: unchanged native Hunt path/context, original tile faces/names, native public moves, no encrypted pool state.");
    });System.exit(0);}catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
