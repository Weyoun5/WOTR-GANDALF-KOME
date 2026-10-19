import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

/** Original PDF pages and their searchable text layer, independent of OS associations. */
public final class KomePdfViewer extends JDialog {
    final ExecutorService worker=Executors.newSingleThreadExecutor(r->{Thread t=new Thread(r,"rulebook-pdf");t.setDaemon(true);return t;});
    PDDocument document;PDFRenderer renderer;final List<String> text=new ArrayList<String>();
    final JLabel pageImage=new JLabel("Loading PDF...",SwingConstants.CENTER),status=new JLabel(" "),count=new JLabel(" / ...");
    final JScrollPane scroll=new JScrollPane(pageImage);
    final JSpinner page=new JSpinner(new SpinnerNumberModel(1,1,1,1));
    final JComboBox<String> zoom=new JComboBox<String>(new String[]{"Fit page","Fit width","100%","150%","200%"});
    final JTextField query=new JTextField(20);final JButton previous=new JButton("Previous"),next=new JButton("Next"),find=new JButton("Find next");
    final javax.swing.Timer resize;volatile boolean closed;boolean ready;volatile int generation;int pages,lastMatch=-1;String lastQuery="";
    KomePdfViewer(Path path){super(Game.C,path.getFileName()+" - Rulebook",false);setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel body=new JPanel(new BorderLayout(6,6));body.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel navigation=new JPanel(new FlowLayout(FlowLayout.LEFT));for(Component c:new Component[]{previous,next,new JLabel("Page"),page,count,zoom})navigation.add(c);
        JPanel search=new JPanel(new FlowLayout(FlowLayout.LEFT));search.add(new JLabel("Search"));search.add(query);search.add(find);JPanel top=new JPanel(new GridLayout(0,1));top.add(navigation);top.add(search);body.add(top,BorderLayout.NORTH);body.add(scroll);body.add(status,BorderLayout.SOUTH);add(body);
        previous.addActionListener(e->page.setValue((Integer)page.getValue()-1));next.addActionListener(e->page.setValue((Integer)page.getValue()+1));page.addChangeListener(e->{status.setText("Page "+page.getValue()+" / "+pages);render();});zoom.addActionListener(e->render());
        find.addActionListener(e->search());query.addActionListener(e->search());
        getRootPane().registerKeyboardAction(e->{query.requestFocusInWindow();query.selectAll();},KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e->{query.requestFocusInWindow();query.selectAll();},KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.META_DOWN_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW);
        resize=new javax.swing.Timer(180,e->{if(zoom.getSelectedIndex()<2)render();});resize.setRepeats(false);scroll.getViewport().addComponentListener(new ComponentAdapter(){public void componentResized(ComponentEvent e){resize.restart();}});
        addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){closed=true;generation++;resize.stop();worker.execute(()->{try{if(document!=null)document.close();}catch(Exception ignored){}});worker.shutdown();}});
        previous.setEnabled(false);next.setEnabled(false);page.setEnabled(false);zoom.setEnabled(false);find.setEnabled(false);
        setSize(880,850);setMinimumSize(new Dimension(600,400));setLocationRelativeTo(Game.C);
        worker.execute(()->{try{
            document=Loader.loadPDF(path.toFile());renderer=new PDFRenderer(document);int total=document.getNumberOfPages();
            PDFTextStripper stripper=new PDFTextStripper();stripper.setSortByPosition(true);
            for(int i=1;i<=total;i++){stripper.setStartPage(i);stripper.setEndPage(i);text.add(normalize(stripper.getText(document)));}
            SwingUtilities.invokeLater(()->{if(closed)return;pages=total;page.setModel(new SpinnerNumberModel(1,1,total,1));count.setText(" / "+total);ready=true;page.setEnabled(true);zoom.setEnabled(true);find.setEnabled(true);status.setText("Search the original PDF text. Ctrl+F / Command+F");render();});
        }catch(Exception e){failure(e);}});
    }
    static String normalize(String value){return java.text.Normalizer.normalize(value,java.text.Normalizer.Form.NFKC).toLowerCase(Locale.ROOT).replaceAll("\\s+"," ").trim();}
    void failure(Exception error){SwingUtilities.invokeLater(()->{if(!closed){pageImage.setText("Unable to open this PDF");status.setText(error.getMessage());}});}
    void search(){if(!ready||closed)return;String term=normalize(query.getText());if(term.isEmpty())return;
        List<Integer> matches=new ArrayList<Integer>();for(int i=0;i<text.size();i++)if(text.get(i).contains(term))matches.add(i);
        if(matches.isEmpty()){status.setText("No matches for \""+query.getText()+"\".");return;}
        int start=term.equals(lastQuery)?lastMatch+1:(Integer)page.getValue()-1;int target=matches.get(0);for(int match:matches)if(match>=start){target=match;break;}
        lastQuery=term;lastMatch=target;page.setValue(target+1);status.setText("\""+query.getText()+"\" - matching page "+(matches.indexOf(target)+1)+" of "+matches.size()+" (page "+(target+1)+").");
    }
    void render(){if(!ready||closed)return;final int number=(Integer)page.getValue()-1,token=++generation,mode=zoom.getSelectedIndex();final Dimension viewport=scroll.getViewport().getExtentSize();
        previous.setEnabled(number>0);next.setEnabled(number+1<pages);
        worker.execute(()->{try{
            if(closed||token!=generation)return;org.apache.pdfbox.pdmodel.common.PDRectangle box=document.getPage(number).getCropBox();float w=box.getWidth(),h=box.getHeight();if(document.getPage(number).getRotation()%180!=0){float swap=w;w=h;h=swap;}
            float scale=mode==0?Math.min((viewport.width-12)/w,(viewport.height-12)/h):mode==1?(viewport.width-12)/w:new float[]{1,1,1,1.5f,2}[mode];scale=Math.max(.15f,Math.min(3,scale));
            final BufferedImage image=renderer.renderImage(number,scale);
            SwingUtilities.invokeLater(()->{if(closed||token!=generation)return;pageImage.setText(null);pageImage.setIcon(new ImageIcon(image));scroll.getVerticalScrollBar().setValue(0);scroll.getHorizontalScrollBar().setValue(0);});
        }catch(Exception e){failure(e);}});
    }
    static KomePdfViewer open(Path path){KomePdfViewer viewer=new KomePdfViewer(path);viewer.setVisible(true);return viewer;}
}
