import java.awt.Color;
import javax.swing.*;
import javax.swing.text.*;

/** Preserve native chat colors while making them readable on dark backgrounds. */
public final class KomeChatTheme {
    static final String ORIGINAL="kome.originalChatColor";
    static boolean dark(){Color bg=UIManager.getColor("TextPane.background");return bg!=null&&brightness(bg)<128;}
    static double brightness(Color c){return .2126*c.getRed()+.7152*c.getGreen()+.0722*c.getBlue();}
    static AttributeSet colors(AttributeSet source){
        SimpleAttributeSet attrs=new SimpleAttributeSet(source==null?SimpleAttributeSet.EMPTY:source);
        Color original=(Color)attrs.getAttribute(ORIGINAL);if(original==null)original=StyleConstants.getForeground(attrs);
        attrs.addAttribute(ORIGINAL,original);Color shown=original;
        if(dark()&&brightness(original)<175){double mix=(175-brightness(original))/(255-brightness(original));shown=new Color((int)(original.getRed()+(255-original.getRed())*mix),(int)(original.getGreen()+(255-original.getGreen())*mix),(int)(original.getBlue()+(255-original.getBlue())*mix));}
        StyleConstants.setForeground(attrs,shown);return attrs;
    }
    static void apply(JTextPane pane){
        AbstractDocument doc=(AbstractDocument)pane.getDocument();
        if(!(doc.getDocumentFilter() instanceof Filter)){
            if(!dark())return;
            doc.setDocumentFilter(new Filter(doc.getDocumentFilter()));
        }
        StyledDocument styled=pane.getStyledDocument();
        for(int start=0;start<styled.getLength();){Element run=styled.getCharacterElement(start);int end=Math.min(run.getEndOffset(),styled.getLength());styled.setCharacterAttributes(start,end-start,colors(run.getAttributes()),false);start=end;}
    }
    static final class Filter extends DocumentFilter {
        final DocumentFilter previous;Filter(DocumentFilter previous){this.previous=previous;}
        public void insertString(FilterBypass fb,int offset,String text,AttributeSet attrs)throws BadLocationException{if(previous==null)fb.insertString(offset,text,colors(attrs));else previous.insertString(fb,offset,text,colors(attrs));}
        public void replace(FilterBypass fb,int offset,int length,String text,AttributeSet attrs)throws BadLocationException{if(previous==null)fb.replace(offset,length,text,colors(attrs));else previous.replace(fb,offset,length,text,colors(attrs));}
        public void remove(FilterBypass fb,int offset,int length)throws BadLocationException{if(previous==null)fb.remove(offset,length);else previous.remove(fb,offset,length);}
    }
}
