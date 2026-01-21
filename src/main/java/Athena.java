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

    // List Function
    private static void printList(List<Task> taskList) {
        System.out.println("\t Your campaign stands as follows:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t %d.%s\n", i + 1, taskList.get(i));
        }
    }

    // Mark Task Function
    private static void markTask(List<Task> taskList, int idx) {
        Task task = taskList.get(idx);
        task.markDone();
        System.out.println("\t Strategy realized. This triumph is recorded:");
        System.out.printf("\t   %s\n", task);
    }

    // Unmark Task Function
    private static void unmarkTask(List<Task> taskList, int idx) {
        Task task = taskList.get(idx);
        task.markNotDone();
        System.out.println("\t Restored. Focus your efforts here once more:");
        System.out.printf("\t   %s\n", task);
    }

    // Add ToDo Task Function
    private static void addTodo(List<Task> taskList, String description) {
        Task task = new Todo(description);
        taskList.add(task);
        System.out.println("\t Understood. A new objective is forged:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t The record shows %d tasks awaiting your mastery!\n", taskList.size());
    }

    // Add Deadline Task Function
    private static void addDeadline(List<Task> taskList, String description, String by) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        System.out.println("\t Understood. A new objective is forged:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t The record shows %d tasks awaiting your mastery!\n", taskList.size());
    }

    // Add Event Task Function
    private static void addEvent(List<Task> taskList, String description, String from, String to) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        System.out.println("\t Understood. A new objective is forged:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t The record shows %d tasks awaiting your mastery!\n", taskList.size());
    } 

    private static void processCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            System.out.print("\t> ");
            String input = br.readLine();
            System.out.println(LINE_BREAK);

            // Handle command variations
            if (input.equals("list")) { // List
                printList(taskList);
            } else if (input.equals("bye")) { // Bye
                break;
            } else if (input.startsWith("mark ")) { // Mark
                String[] arg = input.split(" ");
                int taskIdx = Integer.parseInt(arg[1]) - 1;
                markTask(taskList, taskIdx);
            } else if (input.startsWith("unmark ")) { // Unmark
                String[] arg = input.split(" ");
                int taskIdx = Integer.parseInt(arg[1]) - 1;
                unmarkTask(taskList, taskIdx);
            } else if (input.startsWith("todo ")) { // Create ToDo Task
                String description = input.substring(5);
                addTodo(taskList, description);
            } else if (input.startsWith("deadline ")) { // Create Deadline Task
                String task = input.substring(9);
                String[] args = task.split(" /by ");
                addDeadline(taskList, args[0], args[1]);
            } else if (input.startsWith("event ")) { // Create Event Task
                String task = input.substring(6);
                String[] args = task.split(" /from | /to ");
                addEvent(taskList, args[0], args[1], args[2]);
            } else { // Default Todo task
                addTodo(taskList, input);
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
