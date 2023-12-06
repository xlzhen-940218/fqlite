//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DatetimeConverter {
    static final long UNIX_MIN_DATE = 1262304000000L;
    static final long UNIX_MAX_DATE = 2524608000000L;
    static final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public DatetimeConverter() {
    }

    public static String isMacAbsoluteTime(double timestamp) {
        long time = (978307200L + (long)timestamp) * 1000L;
        if (time > 1262304000000L && time < 2524608000000L) {
            Date d = new Date(time);
            return f.format(d).toString();
        } else {
            return null;
        }
    }

    public static String isUnixEpoch(long timestamp) {
        Date d = new Date(timestamp);
        return f.format(d).toString();
    }

    public static String isJulianDate(double timestamp) {
        return calculateGregorianDate(timestamp).toString();
    }

    private static Date calculateGregorianDate(double jd) {
        int l = (int)jd + 68569;
        int n = 4 * l / 146097;
        l -= (146097 * n + 3) / 4;
        int i = 4000 * (l + 1) / 1461001;
        l = l - 1461 * i / 4 + 31;
        int j = 80 * l / 2447;
        int d = l - 2447 * j / 80;
        l = j / 11;
        int m = j + 2 - 12 * l;
        int y = 100 * (n - 49) + i + l;
        double fraction = jd - Math.floor(jd);
        double dHours = fraction * 24.0;
        int hours = (int)dHours;
        double dMinutes = (dHours - (double)hours) * 60.0;
        int minutes = (int)dMinutes;
        int seconds = (int)((dMinutes - (double)minutes) * 60.0);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UT"));
        cal.set(y, m - 1, d, hours + 12, minutes, seconds);
        return cal.getTime();
    }
}
