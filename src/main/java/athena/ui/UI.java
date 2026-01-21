package athena.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import athena.tasks.Task;
import athena.tasks.TaskManager;
/**
 * Handles all interactions with the user.
 * This includes reading user inputs and printing formatted
 * messages to stdout.
 */
public class UI {
    /* Class Level Constants */
    private static final String LINE_BREAK = "\t____________________________________________________________\n";
    private static final String LOGO =
          "\t    ___  __  __                \n"
        + "\t   / _ |/ /_/ /  ___ ___  ___ _\n"
        + "\t  / __ / __/ _ \\/ -_) _ \\/ _ `/\n"
        + "\t /_/ |_\\__/_//_/\\__/_//_/\\_,_/ \n";
    private BufferedReader br;

    /**
     * Constructs a new UI object.
     */
    public UI() {
      this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Returns user input read from BufferedReader.
     * @return user input.
     * @throws IOException if I/O error occured from BufferedReader.
     */
    public String readInput() throws IOException {
        System.out.print("\t> ");
        return br.readLine(); // return user input 
    }

    /**
     * Prints line break onto stdout.
     */
    public void printLine() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints greeting message onto stdout.
     */
    public void printGreeting() {
        StringBuilder sb = new StringBuilder(LINE_BREAK).append(LOGO).append(LINE_BREAK);
        sb.append("\n\t Geetings. I am Athena 🦉.\n\t Which path leads us to victory today? 👑\n").append(LINE_BREAK);
        System.out.println(sb);
    }

    /**
     * Prints exit message onto stdout.
     */
    public void printExit() {
        System.out.println(LINE_BREAK);
        System.out.println("\t Strategy never rests. I shall remain here, watchful.");
        System.out.println("\n\t\t\t\t\t\t\t~Athena 🦉");
    }

    /**
     * Prints all itmes in the list of tasks to stdout.
     * 
     * @param taskList The task manager object that handles list of tasks.
     */
    public void showTaskList(TaskManager taskList) {
        if (taskList.size() == 0) {
            System.out.println("\t The field is clear. Victory is absolute.");
            return;
        }
        System.out.println("\t Your campaign stands as follows:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t %d.%s\n", i + 1, taskList.get(i));
        }
    }

    /**
     * Prints message for when task is mark as complete to stout.
     * 
     * @param task The task that was marked as complete. 
     */
    public void markComplete(Task task) {
        System.out.println("\t Strategy realized. This triumph is recorded:");
        System.out.printf("\t   %s\n", task);
    }

    /**
     * Prints message for when task is mark as incomplete to stdout.
     * 
     * @param task The task that was marked as incomplete.
     */
    public void markIncomplete(Task task) {
        System.out.println("\t Restored. Focus your efforts here once more:");
        System.out.printf("\t   %s\n", task);
    }

    /**
     * Prints message for when task is added to list successfully to stdout.
     * 
     * @param task The task that was added.
     * @param length The number of tasks in the list.
     */
    public void taskCreated(Task task, int length) {
        System.out.println("\t Understood. A new objective is forged:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t The record shows %d tasks awaiting your mastery!\n", length);
    }

    /**
     * Prints error message to stdout
     * 
     * @param msg The error message
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints custom error message for IndexOutOfBoundsException to stdout.
     */
    public void showIndexOutOfBoundsError() {
        System.out.println("\t You strike at shadows. There is no task at that position.");
    }

    /**
     * Prints custom error message for NumberFormatException to stdout.
     */
    public void showNanError(String nan) {
        System.out.printf(
            "\t The record does not recognize %s. Provide a true integer.\n", 
            nan);
    }
}
