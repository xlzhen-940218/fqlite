//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

public class BLOBCarver {
    static final String jpeg_header = "ffd8ff";
    static final String gif87a_header = "474946383761";
    static final String gif89a_header = "474946383961";
    static final String png_header = "89504e470d0a1a0a";
    static final String ico_header = "00000100";
    static final String pdf_header = "25504446";
    static final String bmp_header = "424d";
    static final String tiff_header = "492049";
    static final String svg_header = "3c737667";
    static final String plist_header = "62706c697374";
    static final String gzip_header = "1f8b";
    static final String ftypheic_header = "6674797068656963";
    static final String ftypheix_header = "6674797068656978";
    static final String ftyphevc_header = "6674797068657663";
    static final String ftyphevx_header = "6674797068657678";
    static final String ftypheim_header = "667479706865696d";
    static final String ftypheis_header = "6674797068656973";
    static final String ftyphevm_header = "667479706865766d";
    static final String ftyphevs_header = "6674797068657673";

    public BLOBCarver() {
    }

    public static boolean isHEIC(String s) {
        if (s.contains("6674797068656963")) {
            return true;
        } else if (s.contains("6674797068656978")) {
            return true;
        } else if (s.contains("6674797068657663")) {
            return true;
        } else if (s.contains("6674797068657678")) {
            return true;
        } else if (s.contains("667479706865696d")) {
            return true;
        } else if (s.contains("6674797068656973")) {
            return true;
        } else if (s.contains("667479706865766d")) {
            return true;
        } else {
            return s.contains("6674797068657673");
        }
    }

    public static boolean isGraphic(String s) {
        return isJPEG(s) || isICO(s) || isGIF(s) || isPNG(s);
    }

    public static boolean isPNG(String s) {
        return s.startsWith("89504e470d0a1a0a");
    }

    public static boolean isJPEG(String s) {
        return s.startsWith("ffd8ff");
    }

    public static boolean isPDF(String s) {
        return s.startsWith("25504446");
    }

    public static boolean isGIF(String s) {
        return s.startsWith("474946383761") || s.startsWith("474946383961");
    }

    public static boolean isSVG(String s) {
        return s.startsWith("3c737667");
    }

    public static boolean isTIFF(String s) {
        return s.startsWith("492049");
    }

    public static boolean isBMP(String s) {
        return s.startsWith("424d");
    }

    public static boolean isPLIST(String s) {
        return s.startsWith("62706c697374");
    }

    public static boolean isGZIP(String s) {
        return s.startsWith("1f8b");
    }

    public static boolean isICO(String s) {
        return s.startsWith("00000100");
    }
}
