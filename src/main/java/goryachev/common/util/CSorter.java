package goryachev.common.util;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSorter.class */
public class CSorter {
    public static void sort(Object[] a) {
        if (a != null) {
            Arrays.sort(a, comparator(false));
        }
    }

    public static void sort(List<?> a) {
        if (a != null) {
            Collections.sort(a, comparator(false));
        }
    }

    public static <T> void sort(T[] tArr, final Function<T, String> conv) {
        if (tArr != null) {
            Arrays.sort(tArr, new Comparator<T>() { // from class: goryachev.common.util.CSorter.1
                @Override // java.util.Comparator
                public int compare(T a, T b) {
                    return CSorter.compareItems(a, b, conv);
                }
            });
        }
    }

    public static <T> void sort(List<T> a, final Function<T, String> conv) {
        if (a != null) {
            Collections.sort(a, new Comparator<T>() { // from class: goryachev.common.util.CSorter.2
                @Override // java.util.Comparator
                public int compare(T a2, T b) {
                    return CSorter.compareItems(a2, b, conv);
                }
            });
        }
    }

    public static void sortReverse(Object[] a) {
        if (a != null) {
            Arrays.sort(a, comparator(true));
        }
    }

    public static void sortReverse(List<?> a) {
        if (a != null) {
            Collections.sort(a, comparator(true));
        }
    }

    public static void collate(Object[] a) {
        if (a != null) {
            Arrays.sort(a, collator());
        }
    }

    public static void collate(List<?> a) {
        if (a != null) {
            Collections.sort(a, collator());
        }
    }

    public static <T> void collate(T[] tArr, final Function<T, String> conv) {
        if (tArr != null) {
            Arrays.sort(tArr, new Comparator<T>() { // from class: goryachev.common.util.CSorter.3
                private final Collator collator = Collator.getInstance();

                @Override // java.util.Comparator
                public int compare(T a, T b) {
                    return CSorter.collateItems(a, b, this.collator, conv);
                }
            });
        }
    }

    public static <T> void collate(List<T> a, final Function<T, String> conv) {
        if (a != null) {
            Collections.sort(a, new Comparator<T>() { // from class: goryachev.common.util.CSorter.4
                private final Collator collator = Collator.getInstance();

                @Override // java.util.Comparator
                public int compare(T a2, T b) {
                    return CSorter.collateItems(a2, b, this.collator, conv);
                }
            });
        }
    }

    public static int compare(int a, int b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static int compare(long a, long b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static int compare(float a, float b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static int compare(double a, double b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static Comparator<Object> comparator() {
        return comparator(false);
    }

    public static Comparator<Object> comparator(final boolean reverse) {
        return new Comparator<Object>() { // from class: goryachev.common.util.CSorter.5
            @Override // java.util.Comparator
            public int compare(Object a, Object b) {
                int rv = CSorter.smartCompare(a, b);
                return reverse ? -rv : rv;
            }
        };
    }

    protected static String toStringValue(Object x) {
        if (x == null) {
            return null;
        }
        return x.toString();
    }

    public static int smartCompare(Object a, Object b) {
        if ((a instanceof Comparable) && (b instanceof Comparable)) {
            if (a.getClass().isAssignableFrom(b.getClass())) {
                return ((Comparable) a).compareTo(b);
            }
            if (b.getClass().isAssignableFrom(a.getClass())) {
                return ((Comparable) b).compareTo(a);
            }
        }
        String sa = toStringValue(a);
        String sb = toStringValue(b);
        if (sa == null) {
            if (sb == null) {
                return 0;
            }
            return -1;
        } else if (sb == null) {
            return 1;
        } else {
            return sa.compareTo(sb);
        }
    }

    protected static Comparator<Object> collator() {
        return new Comparator<Object>() { // from class: goryachev.common.util.CSorter.6
            private Collator collator = Collator.getInstance();

            @Override // java.util.Comparator
            public int compare(Object a, Object b) {
                String sa = CSorter.toStringValue(a);
                String sb = CSorter.toStringValue(b);
                if (sa == null) {
                    if (sb == null) {
                        return 0;
                    }
                    return -1;
                } else if (sb == null) {
                    return 1;
                } else {
                    return this.collator.compare(sa, sb);
                }
            }
        };
    }

    public static <T> int compareItems(T a, T b, Function<T, String> conv) {
        if (a == null) {
            if (b == null) {
                return 0;
            }
            return -1;
        } else if (b == null) {
            return 1;
        } else {
            String sa = conv.apply(a);
            String sb = conv.apply(b);
            if (sa == null) {
                if (sb == null) {
                    return 0;
                }
                return -1;
            } else if (sb == null) {
                return 1;
            } else {
                return sa.compareTo(sb);
            }
        }
    }

    public static <T> int collateItems(T a, T b, Collator collator, Function<T, String> conv) {
        if (a == null) {
            if (b == null) {
                return 0;
            }
            return -1;
        } else if (b == null) {
            return 1;
        } else {
            String sa = conv.apply(a);
            String sb = conv.apply(b);
            if (sa == null) {
                if (sb == null) {
                    return 0;
                }
                return -1;
            } else if (sb == null) {
                return 1;
            } else {
                return collator.compare(sa, sb);
            }
        }
    }
}
