//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JARStarter {
    private static Map<String, Class<?>> PROGRAM_MODES = new HashMap();

    static {
        PROGRAM_MODES.put("gui", GUI.class);
        PROGRAM_MODES.put("nogui", MAIN.class);
        PROGRAM_MODES.put("cli", MAIN.class);
    }

    public JARStarter() {
    }

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (args.length == 0) {
            GUI.main(args);
        } else {
            Class<?> entryPoint = (Class)PROGRAM_MODES.get(args[0]);
            if (entryPoint == null) {
                MAIN.printOptions();
                return;
            }

            String[] argsCopy = args.length > 1 ? (String[])Arrays.copyOfRange(args, 1, args.length) : new String[0];
            entryPoint.getMethod("main", String[].class).invoke((Object)null, argsCopy);
        }

    }
}
