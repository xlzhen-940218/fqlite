package goryachev.common.util.text;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/CharCounter.class */
public class CharCounter {
    public static int count(String s) {
        int count = 0;
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    count++;
                }
            }
        }
        return count;
    }
}
