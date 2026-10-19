import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
public class CardInventory {
 public static void main(String[] args)throws Exception {SwingUtilities.invokeAndWait(()->{try {
  Game game=new Game();Field field=k.class.getDeclaredField("f");field.setAccessible(true);List<String> rows=new ArrayList<String>();
  for(Field list:Game.class.getDeclaredFields())if(list.getType()==ArrayList.class){list.setAccessible(true);Object values=list.get(game);if(values==null)continue;for(Object value:(ArrayList)values)if(value instanceof k){k card=(k)value;ImageIcon icon=(ImageIcon)field.get(card);if(icon!=null)rows.add(list.getName()+"\t"+card.h()+"\t"+icon.getDescription());}}
  Files.write(Paths.get(args[0]),rows,StandardCharsets.UTF_8);
 }catch(Exception e){e.printStackTrace();System.exit(1);}});System.exit(0);}
}
