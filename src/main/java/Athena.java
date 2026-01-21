import java.util.ArrayList;
import java.util.List;
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
        sb.append("\n\t Geetings. I am Athena 🦉.\n\t Which path leads us to victory today? 👑\n").append(LINE_BREAK);
        System.out.println(sb);
    }

    private static void printExit() {
        System.out.println("\t Strategy never rests. I shall remain here, watchful. 🦉");
        System.out.println(LINE_BREAK);
    }

    private static void printList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf("\t %d. %s\n", i + 1, taskList.get(i));
        }
    }

    private static void markTask(List<Task> taskList, int idx) {
        Task task = taskList.get(idx);
        task.markDone();
        System.out.println("\t Conquered. Next objective?");
        System.out.printf("\t   %s\n", task);
    }

    private static void unmarkTask(List<Task> taskList, int idx) {
        Task task = taskList.get(idx);
        task.markNotDone();
        System.out.println("\t Restored. Focus your efforts here once more.");
        System.out.printf("\t   %s\n", task);
    }

    private static void createTask(List<Task> taskList, String label) {
        Task task = new Task(label);
        taskList.add(task);
        System.out.printf("\t added new task: %s\n", label);
    } 

    private static void processCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            System.out.print("\t> ");
            String command = br.readLine();
            System.out.println(LINE_BREAK);

            if (command.equals("list")) {
                printList(taskList);
            } else if (command.equals("bye")) {
                break;
            } else if (command.startsWith("mark ")) {
                String[] inputs = command.split(" ", 2);
                int taskIdx = Integer.parseInt(inputs[1]) - 1;
                markTask(taskList, taskIdx);
            } else if (command.startsWith("unmark ")) {
                String[] inputs = command.split(" ", 2);
                int taskIdx = Integer.parseInt(inputs[1]) - 1;
                unmarkTask(taskList, taskIdx);
            } else {
                // Entire input is the task description
                createTask(taskList, command);
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
