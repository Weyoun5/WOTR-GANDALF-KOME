import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;

/** Live observers and optional third/fourth players; the native pair owns rolls and decks. */
public final class KomeSpectator {
    static final Map<Game,Session> SESSIONS=Collections.synchronizedMap(new WeakHashMap<Game,Session>());
    static final class Session {
        final java.util.List<String> history=new ArrayList<String>();final java.util.List<Peer> viewers=new ArrayList<Peer>();
        volatile BlockingQueue<String> outgoing=new LinkedBlockingQueue<String>(128);
        volatile boolean watching,syncing,additional,allowPlayers;volatile String status="Not connected";volatile ServerSocket listener;volatile Socket incoming;
        int announcedPlayers=-1; // Last count queued on the EDT, which may not be applied yet.
    }
    static final class Peer {final Socket socket;final BlockingQueue<String> queue=new LinkedBlockingQueue<String>(20000);boolean additional;int seat;Peer(Socket socket){this.socket=socket;}}
    static Session session(Game game){synchronized(SESSIONS){Session s=SESSIONS.get(game);if(s==null){s=new Session();SESSIONS.put(game,s);}return s;}}
    static boolean watching(Game game){return session(game).watching;}
    static boolean blocked(Game game){return watching(game);}
    static void thread(String name,Runnable action){Thread t=new Thread(action,name);t.setDaemon(true);t.start();}
    static void close(Socket socket){try{if(socket!=null)socket.close();}catch(IOException ignored){}}
    static String read(BufferedReader in,int limit)throws IOException{StringBuilder line=new StringBuilder();int c;while((c=in.read())!=-1){if(c=='\n')return line.toString();if(c!='\r')line.append((char)c);if(line.length()>limit)throw new IOException("Message exceeds connection limit.");}return line.length()==0?null:line.toString();}
    public static void recorded(Game game,String input){
        Session s=session(game);if(s.watching)return;String line=input.trim();
        if(line.contains(" secureDice")||line.startsWith("<mute_")||line.isEmpty())return;
        synchronized(s){s.history.add(line);for(Peer p:new ArrayList<Peer>(s.viewers))if(!p.queue.offer(line))close(p.socket);}
    }
    static String hello(Game game){return "WOTR-SPECTATOR/2 "+KomeBuild.ID+" "+KomeExpansions.config(game).mask;}
    static int host(Game game,int port)throws IOException{return host(game,port,false);}
    static int host(Game game,int port,boolean players)throws IOException{
        Session s=session(game);if(s.watching)throw new IOException("Disconnect from the spectator/additional-player connection first.");
        if(players&&(!game.z.a||!game.z.b))throw new IOException("Start the normal two-player game as host before hosting additional players.");
        if(s.listener!=null&&!s.listener.isClosed()){if(port!=0&&s.listener.getLocalPort()!=port)throw new IOException("Already listening on port "+s.listener.getLocalPort()+".");s.allowPlayers|=players;return s.listener.getLocalPort();}
        ServerSocket server=new ServerSocket(port);s.listener=server;s.allowPlayers=players;s.status="Listening on port "+server.getLocalPort();
        thread("spectator-accept",()->{while(!server.isClosed())try{Socket socket=server.accept();thread("spectator-handshake",()->accept(game,s,socket));}catch(IOException e){if(!server.isClosed())s.status=e.getMessage();}});return server.getLocalPort();
    }
    static void counts(Game game,Session s){SwingUtilities.invokeLater(()->{
        int extra=0,observers=0;synchronized(s){for(Peer peer:s.viewers)if(!peer.socket.isClosed()){if(peer.additional)extra++;else observers++;}}
        int count=2+extra;if(game.z.a&&game.z.b&&(s.announcedPlayers!=count||KomeSupport.state(game).players!=count)){s.announcedPlayers=count;game.z.a("<players> "+count);}
        if(s.listener!=null&&!s.listener.isClosed())s.status="Port "+s.listener.getLocalPort()+" - "+extra+" additional player(s), "+observers+" spectator(s)";
    });}
    static void accept(Game game,Session s,Socket socket){Peer peer=new Peer(socket);try{
        KomeVersion.checkedExchange(game,socket);
        socket.setSoTimeout(10000);socket.setTcpNoDelay(true);BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8),true);
        String greeting=read(in,256);peer.additional=(hello(game)+" PLAYER").equals(greeting);
        if(!peer.additional&&!(hello(game)+" WATCH").equals(greeting)){out.println("ERROR Use the same gameplay build and expansion selection.");return;}
        synchronized(s){
            if(peer.additional){int n=0;for(Peer p:s.viewers)if(p.additional&&!p.socket.isClosed())n++;if(!s.allowPlayers||!game.z.a||!game.z.b){out.println("ERROR The main host has not enabled additional players.");return;}if(n>=2){out.println("ERROR This game already has four players.");return;}}
            if(s.history.size()>19990){out.println("ERROR Game history is too large for this connection.");return;}
            if(peer.additional){peer.seat=3;for(Peer p:s.viewers)if(p.additional&&p.seat==3&&!p.socket.isClosed())peer.seat=4;}
            peer.queue.add("!SYNC");peer.queue.addAll(s.history);peer.queue.add("!LIVE");s.viewers.add(peer);
        }
        out.println("OK");socket.setSoTimeout(0);counts(game,s);
        thread("spectator-requests",()->{try{String request;while((request=read(in,8192))!=null){final String message=request;if(peer.additional)SwingUtilities.invokeLater(()->moveRequest(game,s,peer,message));}}catch(IOException ignored){}finally{close(socket);}});
        while(!socket.isClosed()){String line=peer.queue.poll(1,TimeUnit.SECONDS);out.println(line==null?"!PING":Base64.getEncoder().encodeToString(line.getBytes(StandardCharsets.UTF_8)));if(out.checkError())break;}
    }catch(Exception e){s.status="Connection closed: "+e.getMessage();}finally{synchronized(s){s.viewers.remove(peer);}close(socket);counts(game,s);}}
    static void connect(Game game,String host,int port){connect(game,host,port,false);}
    static void connect(Game game,String host,int port,boolean player){Session s=session(game);if(game.z.a){s.status="Disconnect from the player connection first.";return;}if(s.watching){s.status="Already connected. Disconnect first.";return;}if(s.listener!=null&&!s.listener.isClosed()){s.status="Stop hosting spectator connections first.";return;}
        s.watching=true;s.additional=player;s.syncing=true;s.outgoing=new LinkedBlockingQueue<String>(128);final BlockingQueue<String> requests=s.outgoing;s.status="Connecting...";
        thread("spectator-client",()->{Socket socket=new Socket();s.incoming=socket;try{
            socket.connect(new InetSocketAddress(host,port),10000);KomeVersion.checkedExchange(game,socket);socket.setSoTimeout(15000);socket.setTcpNoDelay(true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8),true);out.println(hello(game)+(player?" PLAYER":" WATCH"));String response=read(in,1024);if(!"OK".equals(response))throw new IOException(response==null?"Host closed the connection":response);socket.setSoTimeout(0);
            SwingUtilities.invokeAndWait(()->{if(game.k())game.o();if(game.m())game.r();});
            thread("additional-player-send",()->{try{while(!socket.isClosed()){String command=requests.poll(1,TimeUnit.SECONDS);if(command!=null&&!socket.isClosed()){out.println(command);if(out.checkError())break;}}}catch(InterruptedException e){Thread.currentThread().interrupt();}finally{close(socket);}});
            String encoded;while(s.incoming==socket&&(encoded=read(in,500000))!=null){if(s.incoming!=socket)break;if(encoded.equals("!PING"))continue;String line=new String(Base64.getDecoder().decode(encoded),StandardCharsets.UTF_8);
                if(line.equals("!SYNC"))continue;if(line.equals("!LIVE")){s.syncing=false;s.status=player?"Connected as additional player":"Watching live (read-only)";continue;}
                if(line.startsWith("!ERROR ")){s.status=line.substring(7);continue;}
                final String command=line;SwingUtilities.invokeAndWait(()->{if(s.incoming==socket)receive(game,command);});
            }
            if(s.incoming==socket)s.status="Connection closed";
        }catch(Exception e){if(s.incoming==socket){s.status="Connection: "+e.getMessage();if(e instanceof KomeVersion.CheckFailure)KomeVersion.notice(game,e.getMessage());}}finally{close(socket);if(s.incoming==socket){s.syncing=false;s.incoming=null;}/* Stay blocked until explicitly disconnected. */}});
    }
    static void receive(Game game,String command){boolean replay=game.A.b;game.A.b=true;try{game.A.c(command);}finally{game.A.b=replay;}}
    static void disconnect(Game game){Session s=session(game);Socket incoming=s.incoming;s.incoming=null;close(incoming);try{if(s.listener!=null)s.listener.close();}catch(IOException ignored){}s.listener=null;
        synchronized(s){for(Peer peer:s.viewers)close(peer.socket);s.viewers.clear();}s.outgoing.clear();s.watching=false;s.syncing=false;s.additional=false;s.allowPlayers=false;s.status="Disconnected";counts(game,s);
    }
    static boolean view(Game game,boolean free,String password){
        if(free?game.u()==null:game.v()==null)return false;
        boolean ok=free?game.g(password):game.h(password);if(ok){if(free){game.G();game.I();}else{game.H();game.J();}}return ok;
    }
    public static boolean hand(Game game,String password,boolean free){if(!watching(game))return false;if(password!=null&&!view(game,free,password))JOptionPane.showMessageDialog(Game.C,"Incorrect password, or the player has not initialized this hand.");return true;}
    // Forward only explicit piece moves. Extra clients cannot forge roll results,
    // change deck security tokens, or submit arbitrary native interpreter commands.
    public static boolean submit(Game game,String command){Session s=session(game);if(!s.watching)return false;
        if(game.A.b||!s.additional||s.syncing||s.incoming==null||s.incoming.isClosed())return true;
        String marker=d.c("Scribe.141"),from=d.c("Scribe.142"),to=d.c("Scribe.143"),end=d.c("Scribe.144");
        int a=command.indexOf(marker),b=command.indexOf(from),c=command.indexOf(to),e=command.indexOf(end);
        if(a<0||b<a||c<b||e<c){s.status="That action is not available through this connection.";return true;}
        String ids=command.substring(a+marker.length(),b).trim();int source=region(command.substring(b+from.length(),c).trim()),target=region(command.substring(c+to.length(),e).trim());
        if(!ids.matches("[0-9]+(?: +[0-9]+)*")||source<0||target<0||!publicRegion(source)||!publicRegion(target)){s.status="Only board pieces and public counters can be moved from an additional-player connection.";return true;}
        if(!s.outgoing.offer("!MOVE "+source+" "+target+" "+ids))s.status="Too many pending moves; wait for the host.";return true;
    }
    static int region(String name){for(int i=0;i<Game.p.length;i++)if(Game.p[i]!=null&&(Game.p[i].e().equals(name)||d.d(Game.p[i].e()).equals(name)))return i;return -1;}
    static boolean publicRegion(int index){return index>=0&&index<Game.p.length&&index!=121&&index!=122&&index!=123&&index!=124&&index!=180&&index!=181&&index!=182&&index!=194&&index!=198;}
    static void moveRequest(Game game,Session s,Peer peer,String request){try{
        if(peer.socket.isClosed()||!peer.additional||!s.allowPlayers||!game.z.a||!game.z.b)throw new IllegalArgumentException("The main player connection is not active.");
        if(request.startsWith("!ROLL ")){KomeAdditionalRolls.accept(game,peer,request);return;}
        if(!request.matches("!MOVE [0-9]+ [0-9]+(?: [0-9]+){1,500}"))throw new IllegalArgumentException("Unsupported additional-player action.");
        if(KomeRollUi.pending(game.P)||KomeRollUi.pending(game.Q))throw new IllegalArgumentException("Wait for the secure roll to finish, then move again.");
        String[] fields=request.split(" ");int source=Integer.parseInt(fields[1]),target=Integer.parseInt(fields[2]);if(!publicRegion(source)||!publicRegion(target))throw new IllegalArgumentException("Invalid destination.");
        Set<String> ids=new LinkedHashSet<String>();for(int i=3;i<fields.length;i++){aq piece=KomeSupport.piece(game,Integer.parseInt(fields[i]));if(piece==null||piece instanceof k||piece instanceof ax||piece.m()!=Game.p[source])throw new IllegalArgumentException("The piece moved already, or is a private card/tile. Try again.");ids.add(piece.n());}
        game.z.a("$"+Game.h.b+d.c("Scribe.141")+String.join(" ",ids)+" "+d.c("Scribe.142")+Game.p[source].e()+d.c("Scribe.143")+Game.p[target].e()+d.c("Scribe.144"));
    }catch(RuntimeException error){peer.queue.offer("!ERROR "+error.getMessage());}}
    static void removeLegacy(Container parent){for(Component component:parent.getComponents()){if(component instanceof JMenu)removeLegacy(((JMenu)component).getPopupMenu());if(component instanceof JMenuItem&&Arrays.asList("hostobs","connectobs","disconnobs","disconnectobs","allowobs","observe","setBalrog").contains(((JMenuItem)component).getActionCommand()))parent.remove(component);}}
    static JMenuItem item(String name,Runnable action){JMenuItem item=new JMenuItem(name);item.addActionListener(e->action.run());return item;}
    static void install(Game game){
        removeLegacy(Game.C.getJMenuBar());
        Game.C.addWindowListener(new java.awt.event.WindowAdapter(){public void windowClosed(java.awt.event.WindowEvent e){disconnect(game);KomeAudio.stop();if(KomeLogReplay.current!=null&&KomeLogReplay.current.game==game)KomeLogReplay.current.timer.stop();}});
        JMenu multiplayer=KomeMenus.nativeMenu("Controls.48"),menu=new JMenu("Spectators");
        menu.add(item("Host as spectator...",()->show(game,true,false)));menu.add(item("Connect to host as spectator...",()->show(game,false,false)));menu.add(item("Disconnect spectator connection",()->disconnect(game)));menu.addSeparator();
        for(boolean free:new boolean[]{true,false}){menu.add(item("View "+(free?"Free Peoples":"Shadow")+" hand...",()->{if(!watching(game)){JOptionPane.showMessageDialog(Game.C,"Join as a spectator or additional player first.");return;}JPasswordField field=new JPasswordField(20);if(JOptionPane.showConfirmDialog(Game.C,field,"Hand password - "+(free?"Free Peoples":"Shadow"),JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){char[] chars=field.getPassword();String password=new String(chars);Arrays.fill(chars,'\0');hand(game,password,free);}}));menu.add(item("Hide "+(free?"Free Peoples":"Shadow")+" hand",()->{if(watching(game)){if(free)game.o();else game.r();}}));}
        multiplayer.addSeparator();multiplayer.add(menu);multiplayer.add(item("Host additional players...",()->show(game,true,true)));multiplayer.add(item("Connect to host as additional player...",()->show(game,false,true)));multiplayer.add(item("Disconnect additional-player connection",()->disconnect(game)));
    }
    static void show(Game game){show(game,false,false);}
    static void show(Game game,boolean hosting,boolean players){
        String title=hosting?(players?"Host additional players":"Host spectator connections"):(players?"Connect as additional player":"Connect to host as spectator");
        JDialog dialog=new JDialog(Game.C,title,false);JPanel form=new JPanel(new GridLayout(0,1,6,6));form.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));JTextField address=new JTextField(Game.h.f==null||Game.h.f.isEmpty()?"localhost":Game.h.f);Session s=session(game);int defaultPort=s.listener!=null&&!s.listener.isClosed()?s.listener.getLocalPort():Game.h.e>=1024&&Game.h.e<65535?Game.h.e+1:5556;JSpinner port=new JSpinner(new SpinnerNumberModel(defaultPort,1024,65535,1));JLabel status=new JLabel(s.status);
        if(!hosting){form.add(new JLabel("Host address"));form.add(address);}form.add(new JLabel("Spectator / additional-player port"));form.add(port);form.add(new JLabel(hosting?"Keep the normal two-player game connected.":"Use the port shown by the host in this connection menu."));form.add(status);
        JPanel buttons=new JPanel();JButton connect=new JButton(hosting?"Host":"Connect"),disconnect=new JButton("Disconnect"),close=new JButton("Close");buttons.add(connect);buttons.add(disconnect);buttons.add(close);
        connect.addActionListener(e->{if(hosting)try{host(game,(Integer)port.getValue(),players);counts(game,s);}catch(IOException error){s.status=error.getMessage();}else connect(game,address.getText().trim(),(Integer)port.getValue(),players);status.setText(s.status);});disconnect.addActionListener(e->disconnect(game));close.addActionListener(e->dialog.dispose());
        javax.swing.Timer timer=new javax.swing.Timer(400,e->status.setText(s.status));timer.start();dialog.addWindowListener(new java.awt.event.WindowAdapter(){public void windowClosed(java.awt.event.WindowEvent e){timer.stop();}});dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);dialog.add(form);dialog.add(buttons,BorderLayout.SOUTH);dialog.pack();dialog.setMinimumSize(new Dimension(520,dialog.getHeight()));dialog.setSize(600,dialog.getHeight());dialog.setLocationRelativeTo(Game.C);dialog.setVisible(true);
    }
}
