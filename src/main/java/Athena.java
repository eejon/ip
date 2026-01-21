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
        System.out.println("\t Strategy never rests. I shall remain here, watchful.");
        System.out.println("\n\t\t\t\t\t\t\t~Athena 🦉");
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
    private static void markTask(List<Task> taskList, int idx) throws IndexOutOfBoundsException {
        Task task = taskList.get(idx);
        task.markDone();
        System.out.println("\t Strategy realized. This triumph is recorded:");
        System.out.printf("\t   %s\n", task);
    }

    // Unmark Task Function
    private static void unmarkTask(List<Task> taskList, int idx) throws IndexOutOfBoundsException {
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

            // Parse inputs, split into [command, argument(s)]
            String[] inputs = br.readLine().trim().split(" ", 2);

            String command = inputs[0].toLowerCase();
            String arguments = inputs.length > 1 ? inputs[1] : ""; // default ""

            // Handle command variations
            try {
                switch (command) {
                case "list": {printList(taskList);
                    break;
                }

                case "bye": {
                    return;
                }

                case "mark": {
                    int taskIdx = Integer.parseInt(arguments) - 1;
                    markTask(taskList, taskIdx);
                    break;
                }

                case "unmark": {
                    int taskIdx = Integer.parseInt(arguments) -1;
                    unmarkTask(taskList, taskIdx);
                }

                case "todo": {
                    // No arguments
                    if (arguments.isEmpty()) {
                        throw new AthenaException("\tStrategy requires detail. A todo must have a defined objective.");
                    }
                    addTodo(taskList, arguments);
                    break;
                }

                case "deadline": {
                    String[] args = arguments.split(" /by ");
                    // No arguments or no deadline
                    if (args.length < 2 || args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
                        throw new AthenaException("\tStrategy requires detail. A deadline must have a defined objective.");
                    }
                    addDeadline(taskList, args[0], args[1]);
                    break;
                }

                case "event": {
                    String[] args = arguments.split(" /from | /to ");
                    if (args.length < 3 || args[0].trim().isEmpty() || args[1].trim().isEmpty() || args[2].trim().isEmpty()) {
                        throw new AthenaException("\tStrategy requires detail. An event must have a defined objective.");
                    }
                    addEvent(taskList, args[0], args[1], args[2]);
                    break;
                }

                default: 
                    // Handles unrecognized commands
                    throw new AthenaException("\tI do not recognize that tactic. Speak with clarity.");
                }
            } catch (AthenaException e) {

                System.out.println(e.getMessage());

            } catch (NumberFormatException e) {

                System.out.printf("\tThe record does not recognize %s. Provide a true integer.\n", arguments);

            } catch (IndexOutOfBoundsException e) {

                System.out.println("\tYou strike at shadows. There is no task at that position.");

            }
            finally {

                System.out.println(LINE_BREAK);

            }
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
