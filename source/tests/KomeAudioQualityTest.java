import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

/** Verify the upgraded event files can be decoded and opened by the Java 8 mixer. */
public class KomeAudioQualityTest {
    public static void main(String[] args)throws Exception{
        Properties files=new Properties();try(InputStream in=new FileInputStream("audio/events/events.properties")){files.load(in);}
        for(double[] expected:new double[][]{{16,8.35},{28,4.55},{29,3.4},{30,8.65},{52,2.9},{53,7.18},{54,4.55}}){
            int cue=(int)expected[0];File file=new File("audio/events",files.getProperty(Integer.toString(cue)));
            try(AudioInputStream stream=AudioSystem.getAudioInputStream(file)){
                AudioFormat f=stream.getFormat();
                if(f.getSampleRate()!=48000||f.getChannels()!=2||f.getSampleSizeInBits()!=16||!f.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED))throw new AssertionError("Wrong quality format: "+cue);
                if(Math.abs(stream.getFrameLength()/f.getFrameRate()-expected[1])>.001)throw new AssertionError("Wrong trim: "+cue);
                Clip clip=AudioSystem.getClip();try{clip.open(stream);if(clip.getFrameLength()<=0)throw new AssertionError("Empty clip: "+cue);}finally{clip.close();}
            }
        }
        System.out.println("AUDIO QUALITY PASS: all seven 48-kHz stereo PCM clips have expected trims and open on the Java 8 mixer");
    }
}
