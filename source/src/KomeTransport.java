import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.*;

/** Original Incoming/Accept/Done protocol with short queue locks and bounded reads. */
public final class KomeTransport {
    private static final Map<bp,Wire> WIRES=Collections.synchronizedMap(new WeakHashMap<bp,Wire>());
    private static final ThreadLocal<Socket> SESSION=new ThreadLocal<Socket>();
    private static final Method RECEIVE;
    static {
        try{RECEIVE=bp.class.getDeclaredMethod("j");RECEIVE.setAccessible(true);}
        catch(ReflectiveOperationException error){throw new ExceptionInInitializerError(error);}
    }
    private static final class Wire {
        final Socket socket;final InputStream input;
        volatile boolean busy;
        final ByteArrayOutputStream partial=new ByteArrayOutputStream();
        final Queue<String> notices=new ConcurrentLinkedQueue<String>();
        Wire(Socket socket)throws IOException{this.socket=socket;input=socket.getInputStream();socket.setSoTimeout(50);}
        String poll()throws IOException {
            // Retain incomplete UTF-8/default-charset lines across socket timeouts.
            // BufferedReader.readLine() would lose a partial line on timeout.
            while(true){
                int next;
                try{next=input.read();}catch(SocketTimeoutException timeout){return null;}
                if(next<0)throw new EOFException("The other client disconnected.");
                if(next=='\n'){
                    byte[] bytes=partial.toByteArray();partial.reset();int size=bytes.length;
                    if(size>0&&bytes[size-1]=='\r')size--;
                    return new String(bytes,0,size,Charset.defaultCharset());
                }
                partial.write(next);
            }
        }
    }
    private static Wire wire(bp connection)throws IOException {
        if(SESSION.get()!=null&&SESSION.get()!=connection.d)throw new IOException("Connection replaced.");
        synchronized(WIRES){
            Wire wire=WIRES.get(connection);
            if(wire==null||wire.socket!=connection.d){wire=new Wire(connection.d);WIRES.put(connection,wire);}
            return wire;
        }
    }
    public static void notice(bp connection,String text) {
        if(!connection.a)return;
        // The worker initializes this before accepting native commands. Never
        // initialize a socket or write to it on the keyboard event thread.
        Wire wire=WIRES.get(connection);
        if(wire!=null)wire.notices.add(text);
    }
    private static void flushNotices(bp connection,Wire wire)throws IOException {
        String notice;while((notice=wire.notices.poll())!=null)connection.f.println(notice);
        connection.f.flush();if(connection.f.checkError())throw new IOException("Unable to write to the other client.");
    }
    private static String decode(bp connection,Game game,String raw) {
        String line=connection.c(raw); // Preserve native signature extraction.
        if("Disconnecting".equals(line)){disconnect(connection,game,true);return null;}
        if("type".equals(line)||"silence".equals(line)){
            final boolean typing="type".equals(line);
            SwingUtilities.invokeLater(()->{if(connection.a)game.a(typing);});return null;
        }
        return line;
    }
    public static String read(bp connection,Game game) {
        try{
            Wire wire=wire(connection);long deadline=System.nanoTime()+30000000000L;
            while(connection.a){
                String raw=wire.poll();if(raw!=null)return decode(connection,game,raw);
                if(System.nanoTime()>deadline)throw new SocketTimeoutException("The other client stopped responding during a command exchange.");
            }
            throw new EOFException("Connection closed.");
        }catch(IOException error){disconnect(connection,game,true);throw new WireFailure(error);}
    }
    private static final class WireFailure extends RuntimeException {WireFailure(Throwable cause){super(cause);}}
    private static void receive(bp connection) {
        try{RECEIVE.invoke(connection);}
        catch(InvocationTargetException error){Throwable cause=error.getCause();if(cause instanceof RuntimeException)throw (RuntimeException)cause;throw new WireFailure(cause);}
        catch(ReflectiveOperationException error){throw new WireFailure(error);}
    }
    public static void send(bp connection,Game game) {
        try{
            Wire wire=wire(connection);
            connection.f.println("Incoming");connection.f.flush();
            if(connection.f.checkError())throw new IOException("Unable to start command exchange.");
            long deadline=System.nanoTime()+30000000000L;
            while(connection.a){
                if(System.nanoTime()>deadline)throw new SocketTimeoutException("Timed out waiting for the other client to accept commands.");
                String message=read(connection,game);if(message==null)continue;
                if(message.equals("Incoming")){if(connection.b)break;receive(connection);return;}
                if(message.equals("Accept"))break;
            }
            if(!connection.a)return;
            while(connection.a){
                String command;
                synchronized(connection.c){if(connection.c.isEmpty())break;command=(String)connection.c.removeFirst();}
                // Preserve signing and local interpretation order exactly. Neither
                // socket writes nor the interpreter runs under the queue monitor.
                String signature=connection.b(String.valueOf(game.A.b())+command.trim());
                String outgoing=command;
                if(!game.A.c){game.A.a(signature);outgoing=command+" "+signature;}
                connection.f.println(outgoing);
                game.A.c(command);
            }
            connection.f.println("Done");connection.f.flush();
            if(connection.f.checkError())throw new IOException("Unable to finish command exchange.");
            flushNotices(connection,wire);
        }catch(IOException error){disconnect(connection,game,true);throw new WireFailure(error);}
    }
    public static void pump(bp connection,Game game) {
        Socket owned=connection.d;SESSION.set(owned);
        try{
            Wire wire=wire(connection);
            while(connection.a&&!Thread.currentThread().isInterrupted()){
                String raw=wire.poll();
                if(raw!=null){
                    String message=decode(connection,game,raw);
                    if("Incoming".equals(message)){
                        wire.busy=true;
                        try{connection.f.println("Accept");connection.f.flush();receive(connection);}finally{wire.busy=false;}
                    }
                }
                if(!connection.a)break;
                boolean queued;synchronized(connection.c){queued=!connection.c.isEmpty();}
                if(queued){wire.busy=true;try{send(connection,game);}finally{wire.busy=false;}}
                if(connection.a)flushNotices(connection,wire);
            }
        }catch(IOException|RuntimeException error){
            System.err.println("Multiplayer connection ended: "+error.getMessage());
        }finally{try{if(connection.d==owned)disconnect(connection,game,true);}finally{SESSION.remove();}}
    }
    private static boolean drained(bp connection) {
        Wire wire=WIRES.get(connection);
        synchronized(connection.c){return !connection.a||(connection.c.isEmpty()&&(wire==null||!wire.busy));}
    }
    public static void drain(bp connection,Game game) {
        if(drained(connection))return;
        final long deadline=System.nanoTime()+30000000000L;
        if(SwingUtilities.isEventDispatchThread()){
            // Preserve the native caller's synchronous continuation while letting
            // Swing service dialog-close and repaint events during network waits.
            java.awt.SecondaryLoop loop=java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().createSecondaryLoop();
            javax.swing.Timer timer=new javax.swing.Timer(20,null);
            timer.addActionListener(event->{
                if(System.nanoTime()>deadline)disconnect(connection,game,true);
                if(drained(connection)){timer.stop();loop.exit();}
            });
            timer.start();try{loop.enter();}finally{timer.stop();}
        }else{
            while(!drained(connection)){
                if(System.nanoTime()>deadline){disconnect(connection,game,true);break;}
                try{Thread.sleep(10);}catch(InterruptedException interrupted){Thread.currentThread().interrupt();break;}
            }
        }
    }
    public static void disconnect(bp connection,Game game,boolean peer) {
        if(SESSION.get()!=null&&SESSION.get()!=connection.d)return;
        final Socket closed=connection.d;
        boolean wasConnected;
        synchronized(connection.c){wasConnected=connection.a;connection.a=false;connection.c.clear();}
        // Close the socket first: closing a reader before its blocked read can wait.
        try{if(connection.d!=null)connection.d.close();}catch(IOException ignored){}
        try{if(connection.e!=null)connection.e.close();}catch(IOException ignored){}
        WIRES.remove(connection);
        KomeRollUi.cancel(game);
        if(wasConnected)SwingUtilities.invokeLater(()->{
            if(connection.a||connection.d!=closed)return;
            game.b="";game.c="";game.b();game.a(false);
            game.B.q.b(peer?"Disconnected from the other client. Any unfinished secure roll was cancelled.":"Disconnected. Any unfinished secure roll was cancelled.");
        });
    }
}
