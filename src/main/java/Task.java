/**
 * Represents a task with a label, description, and completion status.
 * A task can be marked as complete or incomplete.
 */
public class Task {
    protected boolean isCompleted;
    protected String label;

    /**
     * Creates a new task with the specified label, defaults no description.
     * The task is initially marked as not completed.
     *
     * @param label The label of the task.
     */
    public Task(String label) {
        this.label = label;
        this.isCompleted = false;
    }

    public String getlabel() {
        return this.label;
    }

    public boolean getStatus() {
        return this.isCompleted;
    }

    public void changelabel(String newlabel) {
        this.label = newlabel;
    }

    /**
     * Marks this task as complete.
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markNotDone() {
        this.isCompleted = false;
    }

    /**
     * Returns the status icon of this task.
     * Returns "X" if the task is completed, or a space " " if not completed.
     *
     * @return Status icon representing completion status.
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Returns a string representation of this task.
     * Format: [status_icon] description
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + label;
    }
}
