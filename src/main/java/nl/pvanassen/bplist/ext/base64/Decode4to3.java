package nl.pvanassen.bplist.ext.base64;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/ext/base64/Decode4to3.class */
class Decode4to3 {
    private Decode4to3() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset) {
        if (source[srcOffset + 2] == 61) {
            destination[destOffset] = (byte) ((((Constants.DECODABET[source[srcOffset]] & 255) << 18) | ((Constants.DECODABET[source[srcOffset + 1]] & 255) << 12)) >>> 16);
            return 1;
        } else if (source[srcOffset + 3] == 61) {
            int outBuff = ((Constants.DECODABET[source[srcOffset]] & 255) << 18) | ((Constants.DECODABET[source[srcOffset + 1]] & 255) << 12) | ((Constants.DECODABET[source[srcOffset + 2]] & 255) << 6);
            destination[destOffset] = (byte) (outBuff >>> 16);
            destination[destOffset + 1] = (byte) (outBuff >>> 8);
            return 2;
        } else {
            try {
                int outBuff2 = ((Constants.DECODABET[source[srcOffset]] & 255) << 18) | ((Constants.DECODABET[source[srcOffset + 1]] & 255) << 12) | ((Constants.DECODABET[source[srcOffset + 2]] & 255) << 6) | (Constants.DECODABET[source[srcOffset + 3]] & 255);
                destination[destOffset] = (byte) (outBuff2 >> 16);
                destination[destOffset + 1] = (byte) (outBuff2 >> 8);
                destination[destOffset + 2] = (byte) outBuff2;
                return 3;
            } catch (Exception e) {
                System.out.println(source[srcOffset] + ": " + Constants.DECODABET[source[srcOffset]]);
                System.out.println(source[srcOffset + 1] + ": " + Constants.DECODABET[source[srcOffset + 1]]);
                System.out.println(source[srcOffset + 2] + ": " + Constants.DECODABET[source[srcOffset + 2]]);
                System.out.println(source[srcOffset + 3] + ": " + Constants.DECODABET[source[srcOffset + 3]]);
                return -1;
            }
        }
    }
}
