package goryachev.fx;

import goryachev.common.util.CList;
import goryachev.common.util.SB;
import goryachev.fx.internal.CssTools;
import goryachev.fx.internal.FxCssProp;
import goryachev.fx.internal.StandardFxProperties;
import java.util.Iterator;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxStyleSheet.class */
public class FxStyleSheet extends StandardFxProperties {
    private final CList<Object> elements = new CList<>();

    public Selector selector(Object... sel) {
        return new Selector(sel);
    }

    public void add(Object... sel) {
        for (Object x : sel) {
            if (x instanceof Object[]) {
                add((Object[]) x);
            } else {
                this.elements.add(x);
            }
        }
    }

    public void add(Object ss) {
        this.elements.add(ss);
    }

    public String generateStyleSheet() {
        SB sb = new SB();
        generate(sb);
        return sb.toString();
    }

    protected void generate(SB sb) {
        Iterator<Object> it = this.elements.iterator();
        while (it.hasNext()) {
            Object x = it.next();
            if (x instanceof Selector) {
                ((Selector) x).write(sb, null);
            } else if (x instanceof FxStyleSheet) {
                ((FxStyleSheet) x).generate(sb);
            } else if (x != null) {
                sb.append(x);
            }
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxStyleSheet$Selector.class */
    public static class Selector extends StandardFxProperties {
        protected final String selector;
        protected final CList<Object> items = new CList<>();

        public Selector(Object... sel) {
            this.selector = CssTools.selector(sel);
        }

        public Selector defines(Object... sel) {
            this.items.addAll(sel);
            return this;
        }

        protected static Selector[] chain(Selector[] parents, Selector sel) {
            if (parents == null) {
                return new Selector[]{sel};
            }
            int sz = parents.length;
            Selector[] rv = new Selector[sz + 1];
            System.arraycopy(parents, 0, rv, 0, sz);
            rv[sz] = sel;
            return rv;
        }

        protected void writeSelector(SB sb, boolean first, Object selector) {
            String s;
            if (selector instanceof Selector) {
                s = ((Selector) selector).selector;
            } else {
                s = (String) selector;
            }
            if (!first && !s.startsWith(":")) {
                sb.sp();
            }
            sb.a(s);
        }

        protected void write(SB sb, Selector[] parentSelectors) {
            if (this.items.size() == 0) {
                return;
            }
            boolean epilogue = false;
            CList<Selector> selectors = null;
            Iterator<Object> it = this.items.iterator();
            while (it.hasNext()) {
                Object x = it.next();
                if (x instanceof Selector) {
                    if (selectors == null) {
                        selectors = new CList<>();
                    }
                    selectors.add((Selector) x);
                } else if (x instanceof FxCssProp) {
                    if (!epilogue) {
                        boolean first = true;
                        if (parentSelectors != null) {
                            for (Selector sel : parentSelectors) {
                                writeSelector(sb, first, sel);
                                first = false;
                            }
                        }
                        writeSelector(sb, first, this.selector);
                        sb.a("\n{\n");
                        epilogue = true;
                    }
                    sb.a("\t");
                    ((FxCssProp) x).write(sb);
                    sb.nl();
                } else if (x != null) {
                    throw new Error("?" + x);
                }
            }
            if (epilogue) {
                sb.a("}\n\n");
            }
            if (selectors != null) {
                Iterator<?> it2 = selectors.iterator();
                while (it2.hasNext()) {
                    Selector sel2 = (Selector) it2.next();
                    sel2.write(sb, chain(parentSelectors, this));
                }
            }
        }
    }
}
