import javax.swing.*;

/** Keep native message views and frame validation on Swing's event thread. */
public final class KomeUi {
    private static final ThreadLocal<Boolean> MUTED=new ThreadLocal<Boolean>();
    public static boolean refresh(Game game) {
        if(SwingUtilities.isEventDispatchThread())return false;
        SwingUtilities.invokeLater(()->game.c());return true;
    }
    public static boolean message(m pane,String text,int method) {
        if(SwingUtilities.isEventDispatchThread())return false;
        final boolean muted=pane.e;
        SwingUtilities.invokeLater(()->{
            Boolean previous=MUTED.get();MUTED.set(muted);
            try{if(method==0)pane.a(text);else if(method==1)pane.b(text);else pane.c(text);}
            finally{if(previous==null)MUTED.remove();else MUTED.set(previous);}
        });return true;
    }
    public static boolean muted(m pane){Boolean captured=MUTED.get();return captured==null?pane.e:captured;}
    public static void keyTyped(m pane,java.awt.event.KeyEvent event) {
        char key=event.getKeyChar();
        if(key!='\n'&&key!='\u001b'&&key!='\u007f'&&!event.isControlDown()){
            if(!pane.f.isRunning()&&pane.d.z.a)KomeTransport.notice(pane.d.z,"type");
            pane.f.restart();
        }
    }
    public static void typingStopped(m pane){if(pane.d.z.a)KomeTransport.notice(pane.d.z,"silence");}
    public static void notice(java.io.PrintStream unused,String text,bp connection){KomeTransport.notice(connection,text);}
    public static void alert(java.awt.Component parent,Object message){SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(parent,message));}
}
