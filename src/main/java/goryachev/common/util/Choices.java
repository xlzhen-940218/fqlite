package goryachev.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Choices.class */
public class Choices<T> {
    private final CList<Choice<T>> choices;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Choices$Choice.class */
    public static class Choice<C> {
        protected C choice;
        protected String text;

        protected Choice() {
        }

        public String toString() {
            return this.text;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Choices(Object... choiceTextPairs) {
        this.choices = new CList<>(choiceTextPairs.length / 2);
        int i = 0;
        while (i < choiceTextPairs.length) {
            Choice<T> ch = new Choice<>();
            int i2 = i;
            int i3 = i + 1;
            ch.choice = (T) choiceTextPairs[i2];
            i = i3 + 1;
            ch.text = (String) choiceTextPairs[i3];
            this.choices.add(ch);
        }
    }

    public List<Choice<T>> asList() {
        return new CList((Collection) this.choices);
    }

    public String lookupText(T choice) {
        Iterator<Choice<T>> it = this.choices.iterator();
        while (it.hasNext()) {
            Choice<T> c = it.next();
            if (CKit.equals(c.choice, choice)) {
                return c.text;
            }
        }
        return null;
    }

    public String lookupText(T choice, String defaultValue) {
        String s = lookupText(choice);
        return s == null ? defaultValue : s;
    }

    public T lookupChoice(String text) {
        Iterator<Choice<T>> it = this.choices.iterator();
        while (it.hasNext()) {
            Choice<T> c = it.next();
            if (CKit.equals(c.text, text)) {
                return c.choice;
            }
        }
        return null;
    }

    public T lookupChoice(String text, T defaultValue) {
        T ch = lookupChoice(text);
        return ch == null ? defaultValue : ch;
    }
}
