package goryachev.common.util;

import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CalendarTools.class */
public class CalendarTools {
    public static void set0000(Calendar c) {
        c.set(14, 0);
        c.set(13, 0);
        c.set(12, 0);
        c.set(11, 0);
    }

    public static boolean isSameDate(Calendar a, Calendar b) {
        if (a.get(5) == b.get(5) && a.get(2) == b.get(2) && a.get(1) == b.get(1)) {
            return true;
        }
        return false;
    }

    public static String formatTimeZoneOffset(TimeZone tz) {
        if (tz == null) {
            return null;
        }
        int offset = -tz.getOffset(System.currentTimeMillis());
        SB sb = new SB();
        if (offset >= 0) {
            sb.a('+');
        } else {
            sb.a('-');
            offset = -offset;
        }
        int offset2 = offset / 60000;
        sb.a(CKit.formatTwoDigits(offset2 / 60));
        sb.append(':');
        sb.a(CKit.formatTwoDigits(offset2 % 60));
        return sb.toString();
    }
}
