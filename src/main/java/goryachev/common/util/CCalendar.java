package goryachev.common.util;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CCalendar.class */
public class CCalendar {
    protected final GregorianCalendar cal;

    protected CCalendar() {
        this.cal = new GregorianCalendar();
    }

    public CCalendar(Locale loc) {
        this.cal = new GregorianCalendar(loc);
    }

    public CCalendar(TimeZone tz) {
        this.cal = new GregorianCalendar(tz);
    }

    public CCalendar(TimeZone tz, Locale loc) {
        this.cal = new GregorianCalendar(tz, loc);
    }

    public int getMilliseconds() {
        return this.cal.get(14);
    }

    public void setMilliseconds(int x) {
        this.cal.set(14, x);
    }

    public void addMilliseconds(int x) {
        this.cal.add(14, x);
    }

    public int getSecond() {
        return this.cal.get(13);
    }

    public void setSecond(int x) {
        this.cal.set(13, x);
    }

    public void addSecond(int x) {
        this.cal.add(13, x);
    }

    public int getMinute() {
        return this.cal.get(12);
    }

    public void setMinute(int x) {
        this.cal.set(12, x);
    }

    public void addMinute(int x) {
        this.cal.add(12, x);
    }

    public int getHour() {
        return this.cal.get(11);
    }

    public void setHour(int x) {
        this.cal.set(11, x);
    }

    public void addHour(int x) {
        this.cal.add(11, x);
    }

    public int getDay() {
        return this.cal.get(5);
    }

    public void setDay(int x) {
        this.cal.set(5, x);
    }

    public void addDay(int x) {
        this.cal.add(5, x);
    }

    public int getMonth() {
        return this.cal.get(2);
    }

    public void setMonth(int x) {
        this.cal.set(2, x);
    }

    public void setMonthHuman(int x) {
        this.cal.set(2, x - 1);
    }

    public void addMonth(int x) {
        this.cal.add(2, x);
    }

    public int getYear() {
        return this.cal.get(1);
    }

    public void setYear(int x) {
        this.cal.set(1, x);
    }

    public void addYear(int x) {
        this.cal.add(1, x);
    }

    public int getDayOfWeek() {
        return this.cal.get(7);
    }

    public void setTime(long t) {
        this.cal.setTimeInMillis(t);
    }

    public long getTime() {
        return this.cal.getTimeInMillis();
    }

    public long getTimeInMillis() {
        return this.cal.getTimeInMillis();
    }

    public void setTimeInMillis(long t) {
        this.cal.setTimeInMillis(t);
    }

    @Deprecated
    public void set0000() {
        CalendarTools.set0000(this.cal);
    }

    public int getLastDayOfMonth() {
        return this.cal.getActualMaximum(5);
    }

    public void setTimeZone(TimeZone tz) {
        this.cal.setTimeZone(tz);
    }

    public String getDisplayName(int field, int style) {
        return this.cal.getDisplayName(field, style, Locale.getDefault());
    }

    public String toString() {
        return this.cal.toString();
    }
}
