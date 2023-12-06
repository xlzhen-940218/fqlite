package fqlite.test;

import fqlite.base.GUI;
import fqlite.base.Job;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;

/* loaded from: fqlite_next.jar:fqlite/test/UnitTest.class */
public class UnitTest {
    private static List<TestCase> loadTestCases() {
        List<TestCase> list = new ArrayList<>();
        try {
            URL url = GUI.class.getResource("/testcase.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while (line != null) {
                String line2 = line.trim();
                if (line2.length() == 0 || line2.startsWith(FXMLLoader.CONTROLLER_METHOD_PREFIX)) {
                    line = in.readLine();
                } else {
                    String[] entry = line2.split(":");
                    String filename = entry[0];
                    String checksum = entry[1];
                    if (filename != null && checksum != null) {
                        TestCase c = new TestCase(filename, Integer.parseInt(checksum));
                        list.add(c);
                    }
                    line = in.readLine();
                }
            }
            in.close();
        } catch (IOException err) {
            System.err.println(" Could not open testcases.txt: " + String.valueOf(err));
        }
        return list;
    }

    public static void main(String[] args) {
        List<TestCase> cases = loadTestCases();
        for (TestCase next : cases) {
            Job job = new Job();
            System.out.println("Start analysing database " + next.file);
            int checksum = job.run(next.file);
            System.out.println("checksum" + checksum);
            System.out.println("**************************************************************");
        }
    }
}
