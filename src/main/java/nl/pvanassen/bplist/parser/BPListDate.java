package nl.pvanassen.bplist.parser;

import java.util.Date;
import java.util.GregorianCalendar;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListDate.class */
class BPListDate implements BPListElement<Date> {
    private static final long TIMER_INTERVAL_TIMEBASE = new GregorianCalendar(2001, 0, 1, 1, 0, 0).getTimeInMillis();
    private final Date value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListDate(double value) {
        this.value = new Date(TIMER_INTERVAL_TIMEBASE + (((long) value) * 1000));
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.DATE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Date getValue() {
        return this.value;
    }
}
