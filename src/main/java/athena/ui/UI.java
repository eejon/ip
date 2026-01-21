package athena.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import athena.tasks.Task;
import athena.tasks.TaskManager;

public class UI {
    /* Class Level Constants */
    private static final String LINE_BREAK = "\t____________________________________________________________\n";
    private static final String LOGO =
          "\t    ___  __  __                \n"
        + "\t   / _ |/ /_/ /  ___ ___  ___ _\n"
        + "\t  / __ / __/ _ \\/ -_) _ \\/ _ `/\n"
        + "\t /_/ |_\\__/_//_/\\__/_//_/\\_,_/ \n";
    private BufferedReader br;

    public UI() {
      this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInput() throws IOException {
        System.out.print("\t> ");
        return br.readLine(); // return user input 
    }

    public void printLine() {
        System.out.println(LINE_BREAK);
    }

    public void printGreeting() {
        StringBuilder sb = new StringBuilder(LINE_BREAK).append(LOGO).append(LINE_BREAK);
        sb.append("\n\t Geetings. I am Athena 🦉.\n\t Which path leads us to victory today? 👑\n").append(LINE_BREAK);
        System.out.println(sb);
    }

    public void printExit() {
        System.out.println(LINE_BREAK);
        System.out.println("\t Strategy never rests. I shall remain here, watchful.");
        System.out.println("\n\t\t\t\t\t\t\t~Athena 🦉");
    }

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

    public void markComplete(Task task) {
        System.out.println("\t Strategy realized. This triumph is recorded:");
        System.out.printf("\t   %s\n", task);
    }

    public void markIncomplete(Task task) {
        System.out.println("\t Restored. Focus your efforts here once more:");
        System.out.printf("\t   %s\n", task);
    }

    public void taskCreated(Task task, int length) {
        System.out.println("\t Understood. A new objective is forged:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t The record shows %d tasks awaiting your mastery!\n", length);
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showIndexOutOfBoundsError() {
        System.out.println("\t You strike at shadows. There is no task at that position.");
    }

    public void showNanError(String nan) {
        System.out.printf("\t The record does not recognize %s. Provide a true integer.\n", nan);
    }
}
