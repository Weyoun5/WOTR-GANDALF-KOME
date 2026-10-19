/*
 * Decompiled with CFR 0.152.
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/*
 * Renamed from av
 */
public class av_0 {
    private static MessageDigest a;
    private static Random b;

    static {
        b = new Random();
        try {
            a = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            NoSuchAlgorithmException noSuchAlgorithmException2 = noSuchAlgorithmException;
            noSuchAlgorithmException.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String l() {
        byte[] byArray = new byte[16];
        Random random = b;
        synchronized (random) {
            b.nextBytes(byArray);
        }
        return av_0.a(byArray);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String b(String object) {
        try {
            MessageDigest messageDigest = a;
            synchronized (messageDigest) {
                object = a.digest(((String)object).getBytes("UTF-8"));
            }
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            UnsupportedEncodingException unsupportedEncodingException2 = unsupportedEncodingException;
            unsupportedEncodingException.printStackTrace();
            object = null;
        }
        return av_0.a((byte[])object);
    }

    private static String a(byte[] byArray) {
        StringBuffer stringBuffer = new StringBuffer(byArray.length << 1);
        int n2 = 0;
        while (n2 < byArray.length) {
            int n3 = byArray[n2] & 0xFF;
            if (n3 < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(n3));
            ++n2;
        }
        return stringBuffer.toString().toUpperCase();
    }
}

