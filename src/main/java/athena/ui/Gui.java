package athena.ui;

import java.util.List;

import athena.tasks.Task;

/**
 * Handles all GUI interactions with the user.
 * This includes custom replies and messages for each command.
 */
public class Gui extends Ui {
    private StringBuilder reply;

    /**
     * Constructs a new GUI object.
     */
    public Gui() {
        reply = new StringBuilder();
    }

    /**
     * Returns the response for GUI dialog.
     */
    public String getResponse() {
        return reply.toString().trim();
    }

    @Override
    public void printLine() {
        return;
    }

    @Override
    public void printGreeting() {
        reply.append("Greetings. I am Athena 🦉.\n");
        reply.append("Which path leads us to victory today? 👑");
    }

    @Override
    public void printExit() {
        reply.append("Strategy never rests. I shall remain here, watchful.\n");
        reply.append("\t\t\t\t\t\t\t\t~Athena 🦉");
    }

    @Override
    public void showTaskList(String listString) {
        reply.append(listString);
    }

    @Override
    public void markComplete(Task task) {
        reply.append("Strategy realized. This triumph is recorded:");
        reply.append(String.format("   %s\n", task));
    }

    @Override
    public void markIncomplete(Task task) {
        reply.append("Restored. Focus your efforts here once more:");
        reply.append(String.format("   %s\n", task));
    }

    @Override
    public void taskCreated(Task task, int length) {
        reply.append("Understood. A new objective is forged:");
        reply.append(String.format("   %s\n", task));
        reply.append(String.format("The record shows %d tasks awaiting your mastery!\n", length));
    }

    @Override
    public void showError(String msg) {
        reply.append(msg);
    }

    @Override
    public void showIndexOutOfBoundsError() {
        reply.append("You strike at shadows. There is no task at that position.");
    }

    @Override
    public void showNanError(String nan) {
        reply.append(String.format(
            "The record does not recognize %s. Provide a true integer.\n",
            nan));
    }

    @Override
    public void showDeleted(Task task, int length) {
        reply.append("Struck from the record. The objective is removed:");
        reply.append(String.format("   %s\n", task));
        reply.append(String.format("The record shows %d tasks awaiting your mastery!\n", length));
    }

    @Override
    public void showFoundTasks(List<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            reply.append("The archives reveal no tasks containing \"").append(keyword).append("\".");
        } else {
            reply.append("I have scoured the scrolls. Here is the knowledge you seek:");
            for (int i = 0; i < tasks.size(); i++) {
                reply.append(String.format("%d.\t%s\n", i + 1, tasks.get(i)));
            }
        }
    }

}
