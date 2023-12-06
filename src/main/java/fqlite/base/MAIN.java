//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

public class MAIN {
    public MAIN() {
    }

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        System.out.println("**************************************************************");
        System.out.println("* FQlite - Forensic SQLite Data Recovery Tool                *");
        System.out.println("*                                              version: 2.2 *");
        System.out.println("* Author: D. Pawlaszczyk                                     *");
        System.out.println("* 24/10/2023                                                 *");
        System.out.println("**************************************************************");
        System.out.println("       *                         ");
        System.out.println("      /|\\                       ");
        System.out.println("     /*|O\\                      ");
        System.out.println("    /*/|\\*\\                    ");
        System.out.println("   /X/O|*\\X\\                   ");
        System.out.println("  /*/X/|\\X\\*\\                 ");
        System.out.println(" /O/*/X|*\\O\\X\\                ");
        System.out.println("/*/O/X/|\\X\\O\\*\\              ");
        System.out.println("      |X|                        ");
        System.out.println("      |X|     <Christmas Edition>");
        System.out.println(" \n\n\n                          ");
        Job job = new Job();
        if (args.length == 0) {
            printOptions();
        } else {
            job.path = args[args.length - 1];
            long start = System.currentTimeMillis();
            if (args.length > 1) {
                for(int i = 0; i < args.length; ++i) {
                    String option = args[i];
                    if (option.contains("--wal:")) {
                        job.readWAL = true;
                        job.walpath = option.substring(6);
                        System.out.println("wal-filename: " + job.walpath);
                    } else if (option.contains("--rjournal:")) {
                        job.readRollbackJournal = true;
                        job.rollbackjournalpath = option.substring(11);
                        System.out.println("rollbackjournal-filename: " + job.rollbackjournalpath);
                    }

                    if (option.contains("--threads:")) {
                        try {
                            Global.numberofThreads = Integer.parseInt(option.substring(10));
                            System.out.println("number of threads: " + Global.numberofThreads);
                        } catch (NumberFormatException var9) {
                            System.out.println(" wrong parameter: " + option.substring(10));
                        }
                    }

                    if (option.contains("--loglevel:")) {
                        String loglv = option.substring(11);
                        switch (loglv) {
                            case "ALL":
                                Global.LOGLEVEL = 0;
                                Job.LOGLEVEL = 0;
                                System.out.println("Loglevel was set to ALL");

                                break;
                            case "INFO":
                                Global.LOGLEVEL = 2;
                                Job.LOGLEVEL = 2;
                                System.out.println("Loglevel was set to INFO");

                                break;
                            case "DEBUG":
                                Global.LOGLEVEL = 1;
                                Job.LOGLEVEL = 1;
                                System.out.println("Loglevel was set to DEBUG");

                                break;
                            case "ERROR":
                                Global.LOGLEVEL = 4;
                                Job.LOGLEVEL = 4;
                                System.out.println("Loglevel was set to ERROR");
                                break;
                        }

                        Global.LOGLEVEL = 4;
                    }
                }
            }

            try {
                job.processDB();
            } catch (ExecutionException | InterruptedException var8) {
                var8.printStackTrace();
            }

            long end = System.currentTimeMillis();
            System.out.println("Duration in ms: " + (end - start));
        }

    }

    protected static void printOptions() {
        System.out.println("    ");
        System.out.println("Usage: [mode] [options] <filename>");
        System.out.println("(to analyse a sqlite db-file)");
        System.out.println("    ");
        System.out.println("where mode could be one of the following: gui|nogui|cli ");
        System.out.println("    ");
        System.out.println(" \"gui\" or leave just blank");
        System.out.println("            start program in GUI mode");
        System.out.println(" \"nogui\" or \"cli\" ");
        System.out.println("            start program frome the command line without graphic frontend");
        System.out.println("    ");
        System.out.println("where possible options include: ");
        System.out.println("    ");
        System.out.println("  --wal:<wal-file> ");
        System.out.println("            try to find a companion WAL-file and analyse it");
        System.out.println("  --rjournal:<journal-file> ");
        System.out.println("            try to find a companion rollback journal-file and analyse it");
        System.out.println("  --threads:<number of threads>");
        System.out.println("            start concurrent processing with x threads (only for large files)");
        System.out.println("  --loglevel:<ERROR|INFO|DEBUG|>");
        System.out.println("            logmessage details");
        System.out.println(" ");
        System.out.println("Example:");
        System.out.println("    ");
        System.out.println("  java jar fqlite_<version>.jar nogui --threads:4 --loglevel:ERROR foo.db ");
        System.out.println("  \t    \tstart the program in command line mode ");
        System.out.println("    \t\tuse 4 threads to analyze the data records");
        System.out.println("    \t\tprint only ERROR messages to standard output");
        System.out.println("    \t\tthe name of the database file is <foo.db>");
        System.out.println("    ");
    }
}
