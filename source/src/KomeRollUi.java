import java.lang.reflect.Field;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/** Presentation only; the original secure-roll commitment and arithmetic remain native. */
public final class KomeRollUi {
    private static final Map<Object,Ticket> ACTIVE=new IdentityHashMap<Object,Ticket>();
    private static final class Ticket { volatile boolean closed; JDialog dialog; javax.swing.Timer timeout; }
    public static void open(Object roller,Game game,String title) {
        Ticket ticket=new Ticket();Ticket previous;
        synchronized(ACTIVE){previous=ACTIVE.put(roller,ticket);if(previous!=null)previous.closed=true;}
        if(previous!=null)disposeLater(previous);
        SwingUtilities.invokeLater(()->{
            if(ticket.closed)return;
            Window owner=KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            boolean belongs=false;for(Window window=owner;window!=null;window=window.getOwner())if(window==Game.C)belongs=true;
            if(!belongs)owner=Game.C;
            JDialog dialog=new JDialog(owner,title,Dialog.ModalityType.APPLICATION_MODAL);ticket.dialog=dialog;
            ticket.timeout=new javax.swing.Timer(30000,event->{if(!ticket.closed)KomeTransport.disconnect(game.z,game,true);});
            ticket.timeout.setRepeats(false);
            JProgressBar progress=new JProgressBar();progress.setIndeterminate(true);
            dialog.add(progress);dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog.setSize(300,75);dialog.setLocationRelativeTo(owner);
            if(roller instanceof aZ)((aZ)roller).a=dialog;else ((aV)roller).a=dialog;
            // Closing can occur before the EDT gets here. The queued disposal
            // also covers completion between this check and the modal event loop.
            if(ticket.closed){dialog.dispose();return;}
            ticket.timeout.start();dialog.setVisible(true);
        });
    }
    public static void close(Object roller) {
        Ticket ticket;
        synchronized(ACTIVE){ticket=ACTIVE.remove(roller);if(ticket!=null)ticket.closed=true;}
        if(ticket!=null)disposeLater(ticket);
    }
    private static void disposeLater(Ticket ticket) {
        SwingUtilities.invokeLater(()->{if(ticket.timeout!=null)ticket.timeout.stop();if(ticket.dialog!=null){ticket.dialog.setVisible(false);ticket.dialog.dispose();}});
    }
    public static void cancel(Game game) {
        for(Object roller:new Object[]{game.P,game.Q})if(roller!=null){
            synchronized(roller){
                try{Field pending=roller.getClass().getDeclaredField("c");pending.setAccessible(true);pending.setBoolean(roller,false);}
                catch(ReflectiveOperationException error){throw new IllegalStateException(error);}
                close(roller);
            }
        }
    }
    static boolean pending(Object roller){synchronized(ACTIVE){return ACTIVE.containsKey(roller);}}
}
