import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/** Local PDFs and reference images; never part of game state or network traffic. */
public final class LocalReferences {
    static final Path CUSTOM=Paths.get("references","custom");
    static final String[][] RULEBOOKS={{"War of the Ring","wotr.pdf"},{"Lords of Middle-earth","lome.pdf"},{"Kings of Middle-earth","kome.pdf"},{"Warriors of Middle-earth","wome.pdf"},{"War of the Ring Almanac","almanac.pdf"},{"Expansion Almanac","expansion-almanac.pdf"}};
    static final String[][] CARD_SETS={{"Base game card reference","base"},{"LoME card reference","lome"},{"KoME card reference","kome"},{"WoME card reference","wome"},{"WoME Faction card reference","wome-factions"}};
    static boolean enabled(){return Files.isRegularFile(Paths.get("references","local.enabled"));}
    static boolean isImage(Path path){String name=path.getFileName().toString().toLowerCase(Locale.ROOT);return name.matches(".*\\.(png|jpg|jpeg|gif|bmp)");}
    static List<Path> customFiles()throws IOException{
        List<Path> result=new ArrayList<Path>();if(Files.isDirectory(CUSTOM))try(DirectoryStream<Path> files=Files.newDirectoryStream(CUSTOM)){for(Path p:files)if(Files.isRegularFile(p)&&isImage(p))result.add(p);}
        result.sort(Comparator.comparing(p->p.getFileName().toString().toLowerCase(Locale.ROOT)));return result;
    }
    static List<String[]> entries(Path directory)throws IOException{
        List<String[]> result=new ArrayList<String[]>();
        if(directory.equals(CUSTOM)){for(Path p:customFiles())result.add(new String[]{p.getFileName().toString(),p.getFileName().toString()});return result;}
        Path index=directory.resolve("index.tsv");if(!Files.isRegularFile(index))return result;
        for(String line:Files.readAllLines(index,StandardCharsets.UTF_8)){
            String[] row=line.split("\t",2);if(row.length!=2||!isImage(Paths.get(row[1]))||row[1].startsWith("page-"))continue;
            Path file=directory.resolve(row[1]).normalize();if(file.startsWith(directory.normalize())&&Files.isRegularFile(file))result.add(row);
        }return result;
    }
    static void install(Game game){refreshMenu();}
    static void refreshMenu(){
        JMenu help=KomeMenus.nativeMenu("Controls.63");if(help==null)return;
        for(Component item:help.getMenuComponents())if(item instanceof JComponent&&Boolean.TRUE.equals(((JComponent)item).getClientProperty("local-reference")))help.remove(item);
        JSeparator separator=new JSeparator();separator.putClientProperty("local-reference",true);help.add(separator);
        JMenu books=new JMenu("Rulebooks & Almanacs (PDF)");books.putClientProperty("local-reference",true);
        for(String[] book:RULEBOOKS){Path pdf=Paths.get("references","rulebooks",book[1]);if(Files.isRegularFile(pdf)){JMenuItem item=new JMenuItem(book[0]);item.setToolTipText("Read and search the original PDF inside the client");item.addActionListener(e->openPdf(pdf));books.add(item);}}
        if(books.getItemCount()>0)help.add(books);
        if(enabled())for(String[] set:CARD_SETS){
            Path directory=Paths.get("references",set[1]);
            try{if(!entries(directory).isEmpty()){JMenuItem item=new JMenuItem(set[0]+"...");item.putClientProperty("local-reference",true);item.addActionListener(e->open(directory));help.add(item);}}catch(IOException ignored){}
        }
        JMenuItem custom=new JMenuItem("Custom reference images...");custom.putClientProperty("local-reference",true);custom.addActionListener(e->open(CUSTOM));help.add(custom);
    }
    static void openPdf(Path file){
        KomePdfViewer.open(file);
    }
    static void error(Component owner,String title,Exception error){Throwable cause=error.getCause()==null?error:error.getCause();JOptionPane.showMessageDialog(owner,cause.getMessage(),title,JOptionPane.ERROR_MESSAGE);}
    static JDialog open(Path directory){
        String title=directory.equals(CUSTOM)?"Custom reference images":directory.getFileName()+" reference cards";for(String[] set:CARD_SETS)if(directory.getFileName().toString().equals(set[1]))title=set[0];
        JDialog dialog=new JDialog(Game.C,title,false);dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);dialog.add(new Browser(directory,false));dialog.setMinimumSize(new Dimension(340,300));dialog.setSize(850,800);dialog.setLocationRelativeTo(Game.C);dialog.setVisible(true);return dialog;
    }
    static Path importImage(Path source)throws IOException{
        // Check format and dimensions before copying or decoding a large image.
        try(ImageInputStream stream=ImageIO.createImageInputStream(source.toFile())){
            if(stream==null)throw new IOException("Cannot read "+source.getFileName());Iterator<ImageReader> readers=ImageIO.getImageReaders(stream);
            if(!readers.hasNext()||!isImage(source))throw new IOException("Unsupported image: "+source.getFileName());ImageReader reader=readers.next();
            try{reader.setInput(stream);if((long)reader.getWidth(0)*reader.getHeight(0)>32000000L)throw new IOException(source.getFileName()+" exceeds 32 megapixels. Please resize it before importing.");}finally{reader.dispose();}
        }
        Files.createDirectories(CUSTOM);String name=source.getFileName().toString();int dot=name.lastIndexOf('.');String stem=name.substring(0,dot),ext=name.substring(dot);
        Path target=CUSTOM.resolve(name);int n=2;while(Files.exists(target))target=CUSTOM.resolve(stem+" ("+(n++)+")"+ext);
        return Files.copy(source,target);
    }
    static void manage(Component owner){
        JDialog dialog=new JDialog(Game.C,"Custom reference images",false);dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        DefaultListModel<Path> model=new DefaultListModel<Path>();JList<Path> list=new JList<Path>(model);list.setCellRenderer(new DefaultListCellRenderer(){public Component getListCellRendererComponent(JList<?> l,Object value,int i,boolean selected,boolean focus){return super.getListCellRendererComponent(l,((Path)value).getFileName().toString(),i,selected,focus);}});
        Runnable reload=()->{model.clear();try{for(Path file:customFiles())model.addElement(file);}catch(IOException error){error(dialog,"Could not read references",error);}refreshMenu();};reload.run();
        JPanel body=new JPanel(new BorderLayout(8,8));body.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));body.add(new JLabel("<html>Import cards or reference images (PNG, JPEG, GIF, BMP).<br>Copies are saved with this client and are available under Help.</html>"),BorderLayout.NORTH);body.add(new JScrollPane(list));
        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.RIGHT));JButton add=new JButton("Add images..."),view=new JButton("View"),remove=new JButton("Remove"),close=new JButton("Close");for(JButton button:new JButton[]{add,view,remove,close})buttons.add(button);body.add(buttons,BorderLayout.SOUTH);
        list.addListSelectionListener(e->{view.setEnabled(!list.isSelectionEmpty());remove.setEnabled(!list.isSelectionEmpty());});view.setEnabled(false);remove.setEnabled(false);
        add.addActionListener(e->{
            JFileChooser chooser=new JFileChooser();chooser.setMultiSelectionEnabled(true);chooser.setAcceptAllFileFilterUsed(false);chooser.setFileFilter(new FileNameExtensionFilter("Reference images","png","jpg","jpeg","gif","bmp"));if(chooser.showOpenDialog(dialog)!=JFileChooser.APPROVE_OPTION)return;
            File[] files=chooser.getSelectedFiles();add.setEnabled(false);remove.setEnabled(false);
            new SwingWorker<List<String>,Void>(){
                protected List<String> doInBackground(){List<String> errors=new ArrayList<String>();for(File f:files)try{importImage(f.toPath());}catch(IOException failure){errors.add(failure.getMessage());}return errors;}
                protected void done(){try{List<String> errors=get();if(!errors.isEmpty())JOptionPane.showMessageDialog(dialog,String.join("\n",errors),"Some images could not be imported",JOptionPane.ERROR_MESSAGE);}catch(Exception failure){error(dialog,"Could not import images",failure);}finally{reload.run();add.setEnabled(true);}}
            }.execute();
        });
        view.addActionListener(e->{Path selected=list.getSelectedValue();JDialog viewer=open(CUSTOM);Browser browser=(Browser)viewer.getContentPane().getComponent(0);for(int i=0;i<browser.entries.size();i++)if(browser.entries.get(i)[1].equals(selected.getFileName().toString()))browser.choice.setSelectedIndex(i);});
        remove.addActionListener(e->{List<Path> selected=list.getSelectedValuesList();if(JOptionPane.showConfirmDialog(dialog,"Remove "+selected.size()+" imported image(s)? Your original files are kept.","Remove references",JOptionPane.OK_CANCEL_OPTION)!=JOptionPane.OK_OPTION)return;for(Path file:selected)try{Files.deleteIfExists(file);}catch(IOException failure){error(dialog,"Could not remove image",failure);}reload.run();});
        close.addActionListener(e->dialog.dispose());dialog.add(body);dialog.setSize(620,450);dialog.setMinimumSize(new Dimension(420,300));dialog.setLocationRelativeTo(owner);dialog.setVisible(true);
    }
    static final class Browser extends JPanel {
        // JComboBox uses equals() to find its selected index. A distinct item for
        // every row preserves the identity of cards that share the same title.
        static final class Choice {
            final String label;
            Choice(String label){this.label=label;}
            public String toString(){return label;}
        }
        final List<String[]> entries=new ArrayList<String[]>();final JComboBox<Choice> choice=new JComboBox<Choice>();final Page image=new Page();final JLabel status=new JLabel(" ");int request;
        Browser(Path directory,boolean unused){super(new BorderLayout(6,6));setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
            try{entries.addAll(entries(directory));}catch(IOException error){status.setText("Could not read reference images: "+error.getMessage());}
            Map<String,Integer> totals=new HashMap<String,Integer>(),seen=new HashMap<String,Integer>();
            for(String[] entry:entries)totals.put(entry[0],totals.getOrDefault(entry[0],0)+1);
            for(String[] entry:entries){String label=entry[0];int copy=seen.getOrDefault(label,0)+1;seen.put(label,copy);choice.addItem(new Choice(label+(totals.get(label)>1?" ("+copy+" of "+totals.get(label)+")":"")));}add(choice,BorderLayout.NORTH);
            JScrollPane scroll=new JScrollPane(image);scroll.getViewport().addComponentListener(new ComponentAdapter(){public void componentResized(ComponentEvent e){image.revalidate();image.repaint();}});add(scroll);
            JPanel bottom=new JPanel(new BorderLayout());JPanel navigation=new JPanel(new FlowLayout(FlowLayout.LEFT));JButton prev=new JButton("Previous"),next=new JButton("Next");navigation.add(prev);navigation.add(next);JComboBox<String> zoom=new JComboBox<String>(new String[]{"Fit page","Fit width","100%","150%","200%"});navigation.add(zoom);bottom.add(navigation,BorderLayout.NORTH);bottom.add(status,BorderLayout.SOUTH);add(bottom,BorderLayout.SOUTH);
            prev.addActionListener(e->choice.setSelectedIndex(Math.max(0,choice.getSelectedIndex()-1)));next.addActionListener(e->choice.setSelectedIndex(Math.min(entries.size()-1,choice.getSelectedIndex()+1)));
            zoom.addActionListener(e->{image.zoom=new double[]{-1,0,1,1.5,2}[zoom.getSelectedIndex()];image.revalidate();image.repaint();});
            Runnable load=()->{
                int selected=choice.getSelectedIndex();prev.setEnabled(selected>0);next.setEnabled(selected>=0&&selected<entries.size()-1);if(selected<0)return;
                final int token=++request;Path file=directory.resolve(entries.get(selected)[1]);image.picture=null;image.repaint();status.setText("Loading...");
                new SwingWorker<BufferedImage,Void>(){
                    protected BufferedImage doInBackground()throws Exception{BufferedImage result=ImageIO.read(file.toFile());if(result==null)throw new IOException("Unsupported image");return result;}
                    protected void done(){if(token!=request)return;try{image.picture=get();status.setText((selected+1)+" / "+entries.size());}catch(Exception failure){status.setText("Unable to open image: "+file.getFileName());}image.revalidate();image.repaint();}
                }.execute();
            };
            choice.addActionListener(e->load.run());choice.setEnabled(!entries.isEmpty());zoom.setEnabled(!entries.isEmpty());prev.setEnabled(false);next.setEnabled(false);
            if(entries.isEmpty())status.setText("No images yet. Add them in File > Settings > Custom references.");else load.run();
        }
    }
    static final class Page extends JPanel implements Scrollable {
        BufferedImage picture;double zoom=-1;
        double scale(){if(zoom>0)return zoom;double width=Math.max(1,getParent()==null?600:getParent().getWidth())/(double)(picture==null?600:picture.getWidth());return zoom==0?width:Math.min(width,Math.max(1,getParent()==null?700:getParent().getHeight())/(double)(picture==null?700:picture.getHeight()));}
        public Dimension getPreferredSize(){return picture==null?new Dimension(600,800):new Dimension(Math.max(1,(int)(picture.getWidth()*scale())),Math.max(1,(int)(picture.getHeight()*scale())));}
        protected void paintComponent(Graphics g){super.paintComponent(g);if(picture!=null){Graphics2D draw=(Graphics2D)g.create();draw.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);Dimension size=getPreferredSize();draw.drawImage(picture,Math.max(0,(getWidth()-size.width)/2),Math.max(0,(getHeight()-size.height)/2),size.width,size.height,null);draw.dispose();}}
        public Dimension getPreferredScrollableViewportSize(){return new Dimension(600,700);}public int getScrollableUnitIncrement(Rectangle r,int o,int d){return 24;}public int getScrollableBlockIncrement(Rectangle r,int o,int d){return Math.max(24,o==SwingConstants.VERTICAL?r.height-24:r.width-24);}public boolean getScrollableTracksViewportWidth(){return zoom<=0;}public boolean getScrollableTracksViewportHeight(){return zoom<0;}
    }
}
