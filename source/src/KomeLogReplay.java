import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

/** Offline replay reconstructs each game's component set before reading its moves. */
public final class KomeLogReplay {
    static String selection;
    static Session current;
    static final class Segment {final int start,end,mask;Segment(int s,int e,int m){start=s;end=e;mask=m;}}
    static boolean header(String raw){String line=d.d(raw);return line.startsWith("<game> ")&&line.contains("[WOTR-")&&line.contains(" wotr");}
    static java.util.List<Segment> segments(java.util.List<String> lines){
        java.util.List<Integer> starts=new ArrayList<Integer>();
        for(int i=0;i<lines.size();i++)if(header(lines.get(i)))starts.add(i);
        if(starts.isEmpty())starts.add(0);
        java.util.List<Segment> result=new ArrayList<Segment>();
        for(int n=0;n<starts.size();n++){
            int start=starts.get(n),end=n+1<starts.size()?starts.get(n+1):lines.size();int mask=-1,kings=0;boolean reserves=true;
            for(int i=start;i<end;i++){String line=lines.get(i).trim();
                if(line.startsWith("<expansions> init ")){int found=Integer.parseInt(line.split("\\s+")[2]);if(mask>=0&&mask!=found)throw new IllegalArgumentException("Expansion selections change without a new-game header.");mask=found;}
                if(line.startsWith("<kome> init ")){kings=Integer.parseInt(line.split("\\s+")[2]);reserves=line.contains("reserves-v2");}
            }
            if(!reserves)throw new IllegalArgumentException("This log uses the earlier Kings figure IDs. Open it in the matching earlier client.");
            if(mask<0){String title=lines.get(start);mask=(title.contains("[W")?2:0)|(title.contains("expansion2")||title.contains("[L")||title.contains("[WL")?1:0)|(title.contains("T]")?16:0)|(kings>0?4:0);if(kings==2)mask|=1;}
            if(!KomeExpansions.validMask(mask))throw new IllegalArgumentException("Unsupported expansion selection in replay: "+mask);
            result.add(new Segment(start,end,mask));
        }
        return result;
    }
    static Game fresh(Game old,int mask){
        if(old!=null){KomeSupport.State state=KomeSupport.state(old);if(state.panel!=null)state.panel.dispose();KomeSpectator.disconnect(old);KomeAudio.stop();}
        selection="newWOTR:"+mask;
        try{return new Game();}finally{selection=null;}
    }
    static Session open(Game old,File file)throws IOException{
        java.util.List<String> lines=Files.readAllLines(file.toPath(),Charset.defaultCharset());
        if(lines.isEmpty())throw new IllegalArgumentException("The replay file is empty.");
        java.util.List<Segment> segments=segments(lines);
        if(old.z.a||KomeSpectator.watching(old))throw new IllegalArgumentException("Disconnect from multiplayer before opening a replay.");
        if(current!=null)current.timer.stop();
        Session session=new Session(lines,segments,file.getName());current=session;
        session.game=fresh(old,segments.get(0).mask);session.index=segments.get(0).start;
        session.game.B.q.b("Replay loaded: "+file.getName()+". Use Replay > Step, Play, or Continue.");return session;
    }
    static final class Session {
        final java.util.List<String> lines;final java.util.List<Segment> segments;final javax.swing.Timer timer;
        final String name;Game game;int index,segment;boolean finished;
        Session(java.util.List<String> l,java.util.List<Segment> s,String n){lines=l;segments=s;name=n;timer=new javax.swing.Timer(2000,e->step());timer.setInitialDelay(0);}
        void step(){
            if(finished)return;
            if(index>=lines.size()){finished=true;timer.stop();game.B.q.b("Replay finished: "+name);return;}
            try{
                if(segment+1<segments.size()&&index>=segments.get(segment+1).start){segment++;game=fresh(game,segments.get(segment).mask);}
                String raw=lines.get(index++);if(raw.trim().isEmpty())return;
                boolean replay=game.A.b,animate=game.O;game.A.b=true;game.O=true;
                try{game.A.c(game.z.c(raw));}finally{game.A.b=replay;game.O=animate;}
            }catch(RuntimeException error){timer.stop();finished=true;game.B.q.b("Replay stopped at line "+index+": "+error.getMessage());throw error;}
        }
        void nextBreak(){timer.stop();while(!finished){int before=index;step();if(before<lines.size()&&d.d(lines.get(before)).startsWith("<replay>"))break;}}
    }
    public static boolean action(Game game,ActionEvent event){
        if(!"wotr".equals(Game.e))return false;
        String command=event.getActionCommand();
        if(command.equals("loadreplay")){
            if(game.B.s.showOpenDialog(Game.C)==JFileChooser.APPROVE_OPTION)try{open(game,game.B.s.getSelectedFile());}catch(Exception error){JOptionPane.showMessageDialog(Game.C,error.getMessage(),"Cannot open replay",JOptionPane.ERROR_MESSAGE);}return true;
        }
        Session session=current;if(session==null||session.game!=game)return false;
        if(command.equals("stepreplay")){session.timer.stop();session.step();return true;}
        if(command.equals("playreplay")){if(session.timer.isRunning())session.timer.stop();else if(!session.finished)session.timer.start();return true;}
        if(command.equals("continuereplay")){session.nextBreak();return true;}
        if(command.equals("fasterreplay")){session.timer.setDelay(Math.max(25,(int)(session.timer.getDelay()/1.2)));return true;}
        if(command.equals("slowerreplay")){session.timer.setDelay(Math.min(60000,(int)(session.timer.getDelay()*1.2)));return true;}
        return false;
    }
}
