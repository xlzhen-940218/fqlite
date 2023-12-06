//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.util.Auxiliary;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Protoc {
    public Protoc() {
    }

    public static String decode(String path) {
        String result = "";
        String shellscript = "";

        try {
            String cwd = Path.of("").toAbsolutePath().toString();
            System.out.println("Protoc Path::" + cwd);
            String os = System.getProperty("os.name");
            System.out.println("Using System Property: " + os);
            String separator = FileSystems.getDefault().getSeparator();
            shellscript = cwd + separator + "./proto.run";
            if (Auxiliary.isWindowsSystem()) {
                shellscript = cwd + separator + "protoc.bat";
            } else if (Auxiliary.isMacOS() && Global.WORKINGDIRECTORY != null) {
                shellscript = Global.WORKINGDIRECTORY + Global.separator + "proto.run";
            }

            ProcessBuilder pb = new ProcessBuilder(new String[]{shellscript, path});
            Process p = pb.start();
            p.waitFor();
            result = new String(p.getInputStream().readAllBytes());
            if (result == null || result.length() == 0) {
                result = "not a valid buffer, shellscript:" + shellscript + " path: " + path;
            }

            return result;
        } catch (Exception var8) {
            var8.printStackTrace();
            return "not a valid buffer, shellscript:" + shellscript + " path: " + path;
        }
    }
}
