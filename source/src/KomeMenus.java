import javax.swing.*;

/** Locate the native menus using their translated labels. */
final class KomeMenus {
    static JMenu nativeMenu(String key){
        JMenuBar bar=Game.C.getJMenuBar();String title=d.b(key);
        for(int i=0;i<bar.getMenuCount();i++){JMenu menu=bar.getMenu(i);if(menu!=null&&title.equals(menu.getText()))return menu;}
        throw new IllegalStateException("Missing native menu: "+title);
    }
}
