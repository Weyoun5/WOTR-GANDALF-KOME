/*
 * Decompiled with CFR 0.152.
 */
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class FindReplace {
    private static HashMap a;

    private static void a(String string, int n2) {
        Integer n3 = n2;
        if (a.containsKey(n3)) {
            throw new RuntimeException("Duplicate number" + n2 + " " + string);
        }
        a.put(n3, string);
    }

    public static void main(String[] object) {
        a = new HashMap();
        FindReplace.a("MINH", 0);
        FindReplace.a("SELU", 1);
        FindReplace.a("CARD", 2);
        FindReplace.a("HARL", 3);
        FindReplace.a("TGHA", 4);
        FindReplace.a("FORL", 5);
        FindReplace.a("THIL", 6);
        FindReplace.a("TSHI", 7);
        FindReplace.a("ELUI", 8);
        FindReplace.a("OFOR", 9);
        FindReplace.a("NELU", 10);
        FindReplace.a("GFIE", 11);
        FindReplace.a("ENED", 12);
        FindReplace.a("ARNO", 13);
        FindReplace.a("BUCK", 14);
        FindReplace.a("ANGM", 15);
        FindReplace.a("NDOW", 16);
        FindReplace.a("THAR", 17);
        FindReplace.a("ETTE", 18);
        FindReplace.a("BREE", 19);
        FindReplace.a("MGRA", 20);
        FindReplace.a("WHIL", 21);
        FindReplace.a("DIAU", 22);
        FindReplace.a("SDOW", 23);
        FindReplace.a("ANDR", 24);
        FindReplace.a("TROL", 25);
        FindReplace.a("ANFA", 26);
        FindReplace.a("NDUN", 27);
        FindReplace.a("RIVE", 28);
        FindReplace.a("HOLL", 29);
        FindReplace.a("EREC", 30);
        FindReplace.a("SDUN", 31);
        FindReplace.a("FOBR", 32);
        FindReplace.a("GORO", 33);
        FindReplace.a("MORI", 34);
        FindReplace.a("FOIS", 35);
        FindReplace.a("ORTH", 36);
        FindReplace.a("DAMR", 37);
        FindReplace.a("FANG", 38);
        FindReplace.a("MGUN", 39);
        FindReplace.a("HDEE", 40);
        FindReplace.a("LAME", 41);
        FindReplace.a("WEST", 42);
        FindReplace.a("HPAS", 43);
        FindReplace.a("DDAL", 44);
        FindReplace.a("LORI", 45);
        FindReplace.a("EDOR", 46);
        FindReplace.a("PCEL", 47);
        FindReplace.a("PELA", 48);
        FindReplace.a("UMBA", 49);
        FindReplace.a("ANOR", 50);
        FindReplace.a("EEYR", 51);
        FindReplace.a("LOSS", 52);
        FindReplace.a("GGAT", 53);
        FindReplace.a("EAST", 54);
        FindReplace.a("WHAR", 55);
        FindReplace.a("EVEN", 56);
        FindReplace.a("FORD", 57);
        FindReplace.a("CARR", 58);
        FindReplace.a("DFOR", 59);
        FindReplace.a("OSGI", 60);
        FindReplace.a("MTIR", 61);
        FindReplace.a("RHOS", 62);
        FindReplace.a("NHAR", 63);
        FindReplace.a("NMIR", 64);
        FindReplace.a("WEMU", 65);
        FindReplace.a("NAVA", 66);
        FindReplace.a("WMIR", 67);
        FindReplace.a("WBLA", 68);
        FindReplace.a("EHAR", 69);
        FindReplace.a("SAVA", 70);
        FindReplace.a("WREA", 71);
        FindReplace.a("SITH", 72);
        FindReplace.a("DGUL", 73);
        FindReplace.a("OFRO", 74);
        FindReplace.a("NOTF", 75);
        FindReplace.a("DMAR", 76);
        FindReplace.a("EBLA", 77);
        FindReplace.a("EMIR", 78);
        FindReplace.a("WHEA", 79);
        FindReplace.a("SMIR", 80);
        FindReplace.a("NITH", 81);
        FindReplace.a("EREB", 82);
        FindReplace.a("NRHO", 83);
        FindReplace.a("EEMU", 84);
        FindReplace.a("FHAR", 85);
        FindReplace.a("MMOR", 86);
        FindReplace.a("DALE", 87);
        FindReplace.a("NURN", 88);
        FindReplace.a("NLAN", 89);
        FindReplace.a("IHIL", 90);
        FindReplace.a("MORA", 91);
        FindReplace.a("SRHO", 92);
        FindReplace.a("DAGO", 93);
        FindReplace.a("GORG", 94);
        FindReplace.a("KHAN", 95);
        FindReplace.a("BARA", 96);
        FindReplace.a("VOTC", 97);
        FindReplace.a("AMOU", 98);
        FindReplace.a("VOTL", 99);
        FindReplace.a("SDOR", 100);
        FindReplace.a("ERHU", 101);
        FindReplace.a("NDOR", 102);
        FindReplace.a("SRHU", 103);
        FindReplace.a("NRHU", 104);
        FindReplace.a("ESTR", 105);
        FindReplace.a("GSTR", 106);
        FindReplace.a("RSTR", 107);
        FindReplace.a("WSTR", 108);
        FindReplace.a("LSTR", 109);
        FindReplace.a("HSTR", 110);
        FindReplace.a("MSTR", 111);
        FindReplace.a("DSTR", 112);
        FindReplace.a("SST1", 113);
        FindReplace.a("HUNT", 114);
        FindReplace.a("FELLOWSHIP", 115);
        FindReplace.a("GUIDE", 116);
        FindReplace.a("POL3", 117);
        FindReplace.a("POL2", 118);
        FindReplace.a("POL1", 119);
        FindReplace.a("POL0", 120);
        FindReplace.a("FPCHAR", 121);
        FindReplace.a("FPSTRA", 122);
        FindReplace.a("SACHAR", 123);
        FindReplace.a("SASTRA", 124);
        FindReplace.a("RING1", 125);
        FindReplace.a("RING2", 126);
        FindReplace.a("RING3", 127);
        FindReplace.a("DRING1", 128);
        FindReplace.a("DRING2", 129);
        FindReplace.a("DRING3", 130);
        FindReplace.a("FSP0", 131);
        FindReplace.a("FSP1", 132);
        FindReplace.a("FSP2", 133);
        FindReplace.a("FSP3", 134);
        FindReplace.a("FSP4", 135);
        FindReplace.a("FSP5", 136);
        FindReplace.a("FSP6", 137);
        FindReplace.a("FSP7", 138);
        FindReplace.a("FSP8", 139);
        FindReplace.a("FSP9", 140);
        FindReplace.a("FSP10", 141);
        FindReplace.a("FSP11", 142);
        FindReplace.a("FSP12", 143);
        FindReplace.a("SEA", 144);
        FindReplace.a("SST2", 145);
        FindReplace.a("BOARD1", 146);
        FindReplace.a("BOARD2", 147);
        FindReplace.a("BOARD3", 148);
        FindReplace.a("BOARD4", 149);
        FindReplace.a("BOARD5", 150);
        FindReplace.a("BOARD6", 151);
        FindReplace.a("CURRENT", 152);
        FindReplace.a("CURRENTSA", 153);
        FindReplace.a("CURRENTFP", 154);
        FindReplace.a("FPR", 155);
        FindReplace.a("FPC", 156);
        FindReplace.a("SAR", 157);
        FindReplace.a("SAC", 158);
        FindReplace.a("FPDICE", 159);
        FindReplace.a("SADICE", 160);
        FindReplace.a("FPHAND", 161);
        FindReplace.a("SAHAND", 162);
        FindReplace.a("HUNTPOOL", 163);
        FindReplace.a("HUNTUSED", 164);
        FindReplace.a("HUNTREMOVED", 165);
        try {
            object = new Scanner(new File(object[0]));
            while (((Scanner)object).hasNextLine()) {
                String string = ((Scanner)object).nextLine();
                Iterator iterator = a.keySet().iterator();
                while (iterator.hasNext()) {
                    int n2 = (Integer)iterator.next();
                    string = string.replaceAll("\\[" + n2 + "\\]", "[" + (String)a.get(n2) + "]");
                }
                System.out.println(string);
            }
            ((Scanner)object).close();
            return;
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
            return;
        }
    }
}

