import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Athena {
    /* Class Level Constants */
    private static final String LINE_BREAK = "\t____________________________________________________________\n";
    private static final String LOGO =
          "\t    ___  __  __                \n"
        + "\t   / _ |/ /_/ /  ___ ___  ___ _\n"
        + "\t  / __ / __/ _ \\/ -_) _ \\/ _ `/\n"
        + "\t /_/ |_\\__/_//_/\\__/_//_/\\_,_/ \n";

    private static void printGreeting() {
        StringBuilder sb = new StringBuilder(LINE_BREAK).append(LOGO).append(LINE_BREAK);
        sb.append("\n\t Greetings! I am Athena!\n\t What's on your mind?\n").append(LINE_BREAK);
        System.out.println(sb);
    }

    private static void printExit() {
        System.out.println("\t Until next time.");
        System.out.println(LINE_BREAK);
    }

    private static void processCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> storage = new ArrayList<>();
        while (true) {
            System.out.print("\t> ");
            String command = br.readLine();
            System.out.println(LINE_BREAK);

            // Replace echo logic with add and list logic
            if (command.equals("list")) { 
                for (int i = 0; i < storage.size(); ++i) {
                    System.out.printf("\t %d. %s\n", i + 1, storage.get(i));
                }
            } else if (!command.equals("bye")) { // handles add logic
                storage.add(command);
                System.out.printf("\t added: %s\n", command);
            } else {
                break;
            }
            System.out.println(LINE_BREAK);
        }
    }

    public static void main(String[] args) {
        // Greetings
        printGreeting();
        try {
            processCommand();
        } catch (IOException e) {
            System.out.println("I/O Exception Occurred!");
        }
        // Exit
        printExit();
    }
}
