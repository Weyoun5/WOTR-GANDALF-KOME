import java.awt.*;
import javax.swing.*;

/** Release information is distinct from the local build's date and identity. */
public final class KomeAbout {
    static final String VERSION="1.11";
    static final String RELEASE_DATE="October 19, 2026";
    static void show(){
        JPanel details=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();c.anchor=GridBagConstraints.WEST;c.insets=new Insets(4,8,4,8);
        c.gridx=0;c.gridy=0;c.gridwidth=2;
        JLabel title=new JLabel("War of the Ring - WOTR-GANDALF-KOME");title.setFont(title.getFont().deriveFont(Font.BOLD));details.add(title,c);
        c.gridwidth=1;
        for(String[] row:new String[][]{{"Release version",VERSION},{"Release date",RELEASE_DATE},{"Build date",KomeBuild.DATE},{"Build ID",KomeBuild.ID}}){
            c.gridy++;c.gridx=0;details.add(new JLabel(row[0]+":"),c);c.gridx=1;details.add(new JLabel(row[1]),c);
        }
        JOptionPane.showMessageDialog(Game.C,details,"About",JOptionPane.PLAIN_MESSAGE);
    }
}
