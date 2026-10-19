import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/** Local presentation preferences, separate from game and hand-password settings. */
public final class KomeSettings {
    static final String ORIGINAL_LOOK_AND_FEEL=UIManager.getLookAndFeel().getClass().getName();
    static final Properties VALUES=new Properties();
    static final Set<Object> FONT_OVERRIDES=new HashSet<Object>();
    static final Set<Game> GAMES=Collections.newSetFromMap(new WeakHashMap<Game,Boolean>());
    static final String[] THEMES={"Classic","Modern light","Modern dark"};
    static String theme(){String value=VALUES.getProperty("theme","Classic");return Arrays.asList(THEMES).contains(value)?value:"Classic";}
    static String look(String theme){
        switch(theme){
            case "Modern light":return "com.formdev.flatlaf.FlatLightLaf";
            case "Modern dark":return "com.formdev.flatlaf.FlatDarkLaf";
            default:return ORIGINAL_LOOK_AND_FEEL;
        }
    }
    static {try(InputStream in=new FileInputStream("appearance.properties")){VALUES.load(in);}catch(IOException ignored){}}
    static int number(String key,int fallback){try{return Integer.parseInt(VALUES.getProperty(key));}catch(Exception e){return fallback;}}
    static boolean music(){return Boolean.parseBoolean(VALUES.getProperty("eventMusic","false"));}
    static boolean eventCategory(String key){return Boolean.parseBoolean(VALUES.getProperty(key,"true"));}
    static boolean eventCue(int cue){return eventCategory(cue>=17&&cue<=24?"eventNations":cue>=42&&cue<=51?"eventSovereigns":cue>=56&&cue<=61?"eventFactions":"eventCharacters");}
    static boolean resizablePanels(){return Boolean.parseBoolean(VALUES.getProperty("resizablePanels","false"));}
    static boolean combineWomeHands(){return Boolean.parseBoolean(VALUES.getProperty("combineWomeHands","false"));}
    static void save(){try(OutputStream out=new FileOutputStream("appearance.properties")){VALUES.store(out,"Local appearance and event audio");}catch(IOException e){JOptionPane.showMessageDialog(Game.C,"Could not save appearance settings: "+e.getMessage());}}
    static void apply(){
        String theme=theme();
        String look=look(theme);
        boolean update=!FONT_OVERRIDES.isEmpty()||VALUES.containsKey("font")||VALUES.containsKey("fontSize")||!UIManager.getLookAndFeel().getClass().getName().equals(look);
        // Remove only our overrides, then recover the selected look-and-feel's
        // own weights, fonts, button borders, colors and standard dimensions.
        for(Object key:FONT_OVERRIDES)UIManager.put(key,null);FONT_OVERRIDES.clear();
        if(update){
            UIManager.put("defaultFont",null);UIManager.put("ScrollBar.width",null);
            try{UIManager.setLookAndFeel(look);}catch(Exception error){throw new IllegalStateException("Unable to apply appearance",error);}
        }
        if(VALUES.containsKey("font")||VALUES.containsKey("fontSize")){
            UIDefaults defaults=UIManager.getLookAndFeelDefaults();
            for(Object key:new ArrayList<Object>(defaults.keySet()))if(key.toString().endsWith(".font")){
                Font original=defaults.getFont(key);if(original==null)continue;
                FontUIResource font=new FontUIResource(VALUES.getProperty("font",original.getFamily()),original.getStyle(),Math.max(8,Math.min(36,number("fontSize",original.getSize()))));
                UIManager.put(key,font);FONT_OVERRIDES.add(key);
            }
        }
        if(update)for(Window window:Window.getWindows()){SwingUtilities.updateComponentTreeUI(window);window.validate();window.repaint();}
        for(Game game:GAMES)try{
            java.lang.reflect.Field text=m.class.getDeclaredField("i"),style=m.class.getDeclaredField("j");text.setAccessible(true);style.setAccessible(true);
            javax.swing.text.SimpleAttributeSet attributes=(javax.swing.text.SimpleAttributeSet)style.get(game.B.q);int chatSize=number("chatSize",Game.h.h);String chatFamily=VALUES.getProperty("font",Game.h.g);
            javax.swing.text.StyleConstants.setFontFamily(attributes,chatFamily);javax.swing.text.StyleConstants.setFontSize(attributes,chatSize);
            javax.swing.text.SimpleAttributeSet onlyFont=new javax.swing.text.SimpleAttributeSet();javax.swing.text.StyleConstants.setFontFamily(onlyFont,chatFamily);javax.swing.text.StyleConstants.setFontSize(onlyFont,chatSize);
            JTextPane pane=(JTextPane)text.get(game.B.q);pane.getStyledDocument().setCharacterAttributes(0,pane.getDocument().getLength(),onlyFont,false);game.B.q.c.setFont(VALUES.containsKey("font")||VALUES.containsKey("chatSize")?new Font(chatFamily,Font.PLAIN,chatSize):UIManager.getFont("TextField.font"));
            KomeChatTheme.apply(pane);
            KomeHands.apply(game);
            KomeFigureAppearance.apply(game);
        }catch(ReflectiveOperationException error){throw new IllegalStateException("Unable to apply chat font",error);}
    }
    static void windowSize(){
        String choice=VALUES.getProperty("windowResolution","");if(choice.isEmpty())return;
        if(choice.equals("Maximized")){Game.C.setExtendedState(Game.C.getExtendedState()|Frame.MAXIMIZED_BOTH);return;}
        int width=Game.h.c,height=Game.h.d;
        if(!choice.equals("Saved window size"))try{String[] parts=choice.split(" x ");width=Integer.parseInt(parts[0]);height=Integer.parseInt(parts[1]);}catch(RuntimeException ignored){return;}
        GraphicsConfiguration gc=Game.C.getGraphicsConfiguration();Rectangle bounds=new Rectangle(gc.getBounds());Insets in=Toolkit.getDefaultToolkit().getScreenInsets(gc);
        bounds.x+=in.left;bounds.y+=in.top;bounds.width-=in.left+in.right;bounds.height-=in.top+in.bottom;
        Game.C.setExtendedState(Frame.NORMAL);Game.C.setSize(Math.min(Math.max(640,width),bounds.width),Math.min(Math.max(480,height),bounds.height));
        Game.C.setLocation(Math.max(bounds.x,Math.min(Game.C.getX(),bounds.x+bounds.width-Game.C.getWidth())),Math.max(bounds.y,Math.min(Game.C.getY(),bounds.y+bounds.height-Game.C.getHeight())));
        Game.C.validate();
    }
    static void install(Game game){
        GAMES.add(game);
        JMenu file=KomeMenus.nativeMenu("Controls.47");
        for(Component item:file.getMenuComponents())if(item instanceof JMenuItem&&"kome-settings".equals(((JMenuItem)item).getActionCommand()))return;
        JMenuItem settings=new JMenuItem("Settings...");settings.setActionCommand("kome-settings");settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA,InputEvent.CTRL_DOWN_MASK));settings.addActionListener(e->show(game));file.insert(settings,Math.max(0,file.getItemCount()-2));
        JMenuItem about=new JMenuItem("About");about.setActionCommand("kome-about");about.addActionListener(e->KomeAbout.show());file.insert(about,Math.max(0,file.getItemCount()-2));apply();windowSize();
    }
    static void show(Game game){
        JDialog dialog=new JDialog(Game.C,"Settings",false);dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);JPanel form=new JPanel(new GridBagLayout());form.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));GridBagConstraints c=new GridBagConstraints();c.insets=new Insets(5,0,5,14);c.anchor=GridBagConstraints.WEST;c.fill=GridBagConstraints.HORIZONTAL;c.gridy=0;
        JComboBox<String> theme=new JComboBox<String>(THEMES);theme.setSelectedItem(theme());
        JCheckBox originalFonts=new JCheckBox("Use original fonts",!VALUES.containsKey("font")&&!VALUES.containsKey("fontSize")&&!VALUES.containsKey("chatSize"));
        JComboBox<String> family=new JComboBox<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());family.setSelectedItem(VALUES.getProperty("font",UIManager.getLookAndFeelDefaults().getFont("Label.font").getFamily()));family.setPrototypeDisplayValue("Noto Sans Display");
        JSpinner size=new JSpinner(new SpinnerNumberModel(Math.max(8,Math.min(36,number("fontSize",UIManager.getLookAndFeelDefaults().getFont("Label.font").getSize()))),8,36,1));
        JSpinner chat=new JSpinner(new SpinnerNumberModel(Math.max(8,Math.min(36,number("chatSize",Game.h.h))),8,36,1));
        JCheckBox music=new JCheckBox("Play TTS event music and character cues",music());JSlider volume=new JSlider(0,100,number("musicVolume",65));volume.setMajorTickSpacing(25);volume.setPaintTicks(true);volume.setPaintLabels(true);
        String[] audioKeys={"eventNations","eventSovereigns","eventCharacters","eventFactions"};
        String[] audioLabels={"Nations going to war","Sovereign awakening / corruption","Minion / hero promotion and arrival","WoME faction entry (once per faction per game)"};
        JCheckBox[] audioChoices=new JCheckBox[audioKeys.length];JPanel audioOptions=new JPanel(new GridLayout(0,1,0,2));audioOptions.setBorder(BorderFactory.createEmptyBorder(0,18,0,0));
        for(int i=0;i<audioKeys.length;i++){audioChoices[i]=new JCheckBox(audioLabels[i],eventCategory(audioKeys[i]));audioOptions.add(audioChoices[i]);}
        Runnable audioControls=()->{for(JCheckBox choice:audioChoices)choice.setEnabled(music.isSelected());volume.setEnabled(music.isSelected());};music.addActionListener(e->audioControls.run());audioControls.run();
        JCheckBox panels=new JCheckBox("Resizable dividers (requires restarting)",resizablePanels());
        JCheckBox hands=new JCheckBox("Combine WoME extra hands",combineWomeHands());
        JCheckBox heroes=new JCheckBox("Use LoME Aragorn and Gandalf the White figures",KomeFigureAppearance.lords());
        heroes.setToolTipText("Local artwork only. Applies immediately without changing figures, abilities or expansion rules.");
        hands.setToolTipText("Show each side's extra cards in its regular hand panel. Local display only; passwords and card locations are unchanged.");
        JButton references=new JButton("Manage images...");references.addActionListener(e->LocalReferences.manage(dialog));
        JComboBox<String> resolution=new JComboBox<String>(new String[]{"Saved window size","1024 x 768","1280 x 800","1366 x 768","1600 x 900","1920 x 1080","2560 x 1440","Maximized"});resolution.setSelectedItem(VALUES.getProperty("windowResolution","Saved window size"));
        Runnable fontControls=()->{family.setEnabled(!originalFonts.isSelected());size.setEnabled(!originalFonts.isSelected());chat.setEnabled(!originalFonts.isSelected());};originalFonts.addActionListener(e->fontControls.run());fontControls.run();
        for(Object[] row:new Object[][]{{"Theme",theme},{"Fonts",originalFonts},{"Interface font",family},{"Font size",size},{"Chat font size",chat},{"Window resolution",resolution},{"Panel layout",panels},{"Hands",hands},{"Figures",heroes},{"Custom references",references},{"Event audio",music},{"",audioOptions},{"Event volume",volume}}){c.gridx=0;c.weightx=0;form.add(new JLabel((String)row[0]),c);c.gridx=1;c.weightx=1;form.add((Component)row[1],c);c.gridy++;}
        JLabel help=new JLabel("<html>Original fonts, panel layout and preference sizes are the defaults.<br>Resolution sets the window size, limited to your screen.<br>Map zoom stays in View. Event audio is separate from dice sounds.</html>");c.gridx=0;c.gridwidth=2;form.add(help,c);
        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.RIGHT));JButton reset=new JButton("Reset panel sizes"),test=new JButton("Preview Rohan cue"),apply=new JButton("Apply"),close=new JButton("Close");buttons.add(apply);buttons.add(close);c.gridy++;form.add(test,c);c.gridy++;form.add(reset,c);
        reset.setEnabled(KomeLayout.ROOTS.containsKey(game));reset.setToolTipText("Restore saved panel dimensions after moving resizable dividers");
        Runnable commit=()->{VALUES.setProperty("theme",theme.getSelectedItem().toString());if(originalFonts.isSelected()){VALUES.remove("font");VALUES.remove("fontSize");VALUES.remove("chatSize");}else{VALUES.setProperty("font",family.getSelectedItem().toString());VALUES.setProperty("fontSize",size.getValue().toString());VALUES.setProperty("chatSize",chat.getValue().toString());}String chosen=resolution.getSelectedItem().toString();boolean resize=!chosen.equals(VALUES.getProperty("windowResolution","Saved window size"));VALUES.setProperty("windowResolution",chosen);VALUES.setProperty("resizablePanels",Boolean.toString(panels.isSelected()));VALUES.setProperty("combineWomeHands",Boolean.toString(hands.isSelected()));VALUES.setProperty("eventMusic",Boolean.toString(music.isSelected()));VALUES.setProperty("musicVolume",Integer.toString(volume.getValue()));save();apply();if(resize)windowSize();if(!music.isSelected())KomeAudio.stop();};
        apply.addActionListener(e->{VALUES.setProperty("lomeHeroFigures",Boolean.toString(heroes.isSelected()));for(int i=0;i<audioKeys.length;i++)VALUES.setProperty(audioKeys[i],Boolean.toString(audioChoices[i].isSelected()));commit.run();});close.addActionListener(e->dialog.dispose());reset.addActionListener(e->KomeLayout.reset(game));test.addActionListener(e->KomeAudio.preview(20,volume.getValue()));
        dialog.add(new JScrollPane(form));dialog.add(buttons,BorderLayout.SOUTH);dialog.pack();Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();dialog.setSize(Math.min(dialog.getWidth(),screen.width-80),Math.min(dialog.getHeight(),screen.height-100));dialog.setMinimumSize(new Dimension(500,400));dialog.setLocationRelativeTo(Game.C);dialog.setVisible(true);
    }
}
