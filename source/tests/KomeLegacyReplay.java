import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;

public class KomeLegacyReplay {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
            Path fixtures=Paths.get("../tests/fixtures");
            game.A.a(fixtures.resolve("reserves-v2-before-polish.wotr").toFile());
            String old=new String(Base64.getUrlDecoder().decode(new String(Files.readAllBytes(fixtures.resolve("reserves-v2-before-polish.txt")),StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
            StringBuilder expected=new StringBuilder();
            for(String row:old.split(";")){
                String[] fields=row.split(",");
                if(fields[0].equals("p")&&state.homes.get(KomeSupport.piece(game,Integer.parseInt(fields[1])))<0)continue;
                expected.append(row).append(';');
            }
            String actual=new String(Base64.getUrlDecoder().decode(KomeSupport.snapshot(game)),StandardCharsets.UTF_8);
            // Current snapshots additionally track ordinary Hunt-pool locations.
            // Every field stored by the legacy fixture must still match exactly.
            Set<String> actualRows=new HashSet<String>(Arrays.asList(actual.split(";")));
            for(String row:expected.toString().split(";"))if(!actualRows.contains(row))throw new AssertionError("Earlier reserve save changed "+row);
            if(state.referenceArchive.h()!=13)throw new AssertionError("Reference copies must stay outside the playing regions");
            System.out.println("LEGACY REPLAY PASS (previous reserve build save preserved; redundant reference copies removed)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
