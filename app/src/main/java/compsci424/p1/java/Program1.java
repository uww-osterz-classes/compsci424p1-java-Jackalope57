/* COMPSCI 424 Program 1
 * Name: Jack Lemm
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for this program.
 */
public class Program1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> actions = new ArrayList<>();

        // accept commands until "end" is entered
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("end")) {
                break;
            }
            actions.add(command);
        }

        // create Version1 and Version2 objects
        Version1 version1 = new Version1(16);
        Version2 version2 = new Version2(16);

        // run the command sequence with Version1
        System.out.println("Version 1:");
        for (String action : actions) {
            String[] parts = action.split(" ");
            String command = parts[0];
            int pid = Integer.parseInt(parts[1]);

            switch (command) {
                case "create":
                    version1.create(pid);
                    break;
                case "destroy":
                    version1.destroy(pid);
                    break;
            }
            version1.showProcessInfo();
            System.out.println();
        }

        // run the command sequence with Version2
        System.out.println("\nVersion 2:");
        for (String action : actions) {
            String[] parts = action.split(" ");
            String command = parts[0];
            int pid = Integer.parseInt(parts[1]);

            switch (command) {
                case "create":
                    version2.create(pid);
                    break;
                case "destroy":
                    version2.destroy(pid);
                    break;
            }
            version2.showProcessInfo();
            System.out.println();
        }

        // measure execution time for Version1
        long startTimeV1 = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            for (String action : actions) {
                String[] parts = action.split(" ");
                String command = parts[0];
                int pid = Integer.parseInt(parts[1]);

                switch (command) {
                    case "create":
                        version1.create(pid);
                        break;
                    case "destroy":
                        version1.destroy(pid);
                        break;
                }
            }
        }
        long endTimeV1 = System.nanoTime();
        long runningTimeV1 = (endTimeV1 - startTimeV1) / 1_000_000; // Convert to milliseconds

        // measure execution time for Version2
        long startTimeV2 = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            for (String action : actions) {
                String[] parts = action.split(" ");
                String command = parts[0];
                int pid = Integer.parseInt(parts[1]);

                switch (command) {
                    case "create":
                        version2.create(pid);
                        break;
                    case "destroy":
                        version2.destroy(pid);
                        break;
                }
            }
        }
        long endTimeV2 = System.nanoTime();
        long runningTimeV2 = (endTimeV2 - startTimeV2) / 1_000_000; // convert to milliseconds

        // Display running times
        System.out.println("\nVersion 1 running time: " + runningTimeV1 + " milliseconds");
        System.out.println("Version 2 running time: " + runningTimeV2 + " milliseconds");

        scanner.close();
    }
}