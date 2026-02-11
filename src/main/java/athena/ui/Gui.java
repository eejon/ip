package athena.ui;

import java.util.List;

import athena.tasks.Task;

/**
 * Handles all GUI interactions with the user.
 * This includes custom replies and messages for each command.
 */
public class Gui extends Ui {
    private Response reply;
    private StringBuilder message;

    /**
     * Constructs a new GUI object.
     */
    public Gui() {
        message = new StringBuilder();
    }

    /**
     * Returns the response for GUI dialog.
     */
    public Response getResponse() {
        return reply;
    }

    @Override
    public void printLine() {
        return;
    }

    @Override
    public void printGreeting() {
        message.append("Greetings. I am Athena 🦉.\n");
        message.append("Which path leads us to victory today? 👑");
        reply = Response.standard(message.toString().trim());
    }

    @Override
    public void printExit() {
        message.append("Strategy never rests. I shall remain here, watchful.\n");
        message.append("\t\t\t\t\t\t\t\t~Athena 🦉");
        reply = Response.standard(message.toString().trim());
    }

    @Override
    public void showTaskList(String listString) {
        message.append(listString);
        reply = Response.standard(message.toString().trim());
    }

    @Override
    public void markComplete(Task task) {
        message.append("Strategy realized. This triumph is recorded:");
        message.append(String.format("   %s\n", task));
        reply = Response.success(message.toString().trim());
    }

    @Override
    public void markIncomplete(Task task) {
        message.append("Restored. Focus your efforts here once more:");
        message.append(String.format("   %s\n", task));
        reply = Response.success(message.toString().trim());
    }

    @Override
    public void taskCreated(Task task, int length) {
        message.append("Understood. A new objective is forged:");
        message.append(String.format("   %s\n", task));
        message.append(String.format("The record shows %d tasks awaiting your mastery!\n", length));
        reply = Response.success(message.toString().trim());
    }

    @Override
    public void showError(String msg) {
        message.append(msg);
        reply = Response.error(message.toString().trim());
    }

    @Override
    public void showIndexOutOfBoundsError() {
        message.append("You strike at shadows. There is no task at that position.");
        reply = Response.error(message.toString().trim());
    }

    @Override
    public void showNanError(String nan) {
        message.append(String.format(
            "The record does not recognize %s. Provide a true integer.\n",
            nan));
        reply = Response.error(message.toString().trim());
    }

    @Override
    public void showDeleted(Task task, int length) {
        message.append("Struck from the record. The objective is removed:");
        message.append(String.format("   %s\n", task));
        message.append(String.format("The record shows %d tasks awaiting your mastery!\n", length));
        reply = Response.success(message.toString().trim());
    }

    @Override
    public void showFoundTasks(List<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            message.append("The archives reveal no tasks containing \"").append(keyword).append("\".");
        } else {
            message.append("I have scoured the scrolls. Here is the knowledge you seek:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append(String.format("%d.\t%s\n", i + 1, tasks.get(i)));
            }
        }
        reply = Response.standard(message.toString().trim());
    }

}
