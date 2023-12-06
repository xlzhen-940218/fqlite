//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class FQTableComparator<T> implements Comparator<T> {
    public FQTableComparator() {
    }

    public int compare(Object oo1, Object oo2) {
        String o1 = oo1.toString();
        String o2 = oo2.toString();
        if (o1.length() == 19 && o2.length() == 19 && o1.charAt(2) == '/' && o1.charAt(5) == '/' && o2.charAt(2) == '/' && o2.charAt(5) == '/') {
            return this.compareDates(o1, o2);
        } else {
            boolean isFirstNumeric = o1.matches("\\d+");
            boolean isSecondNumeric = o2.matches("\\d+");
            if (isFirstNumeric) {
                return isSecondNumeric ? Integer.valueOf(o1).compareTo(Integer.valueOf(o2)) : -1;
            } else if (isSecondNumeric) {
                return 1;
            } else {
                try {
                    Integer.parseInt(o1);
                    isFirstNumeric = true;
                } catch (NumberFormatException var9) {
                    isFirstNumeric = false;
                }

                try {
                    Integer.parseInt(o2);
                    isSecondNumeric = true;
                } catch (NumberFormatException var8) {
                    isSecondNumeric = false;
                }

                if (isFirstNumeric) {
                    if (isSecondNumeric) {
                        int intCompare = Integer.valueOf(o1.split("[^0-9]")[0]).compareTo(Integer.valueOf(o2.split("[^0-9]")[0]));
                        return intCompare == 0 ? o1.compareToIgnoreCase(o2) : intCompare;
                    } else {
                        return -1;
                    }
                } else {
                    return isSecondNumeric ? 1 : o1.compareToIgnoreCase(o2);
                }
            }
        }
    }

    public int compareDates(String o1, String o2) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        try {
            return f.parse(o1).compareTo(f.parse(o2));
        } catch (ParseException var5) {
            throw new IllegalArgumentException(var5);
        }
    }
}
