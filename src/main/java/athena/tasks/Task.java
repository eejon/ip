package athena.tasks;

import java.time.LocalDate;
/**
 * Represents a task with a label, and completion status.
 * A task can be marked as complete or incomplete.
 */
public abstract class Task implements Comparable<Task> {
    public static final String STATUS_COMPLETE = "1";
    public static final String STATUS_INCOMPLETE = "0";
    private boolean isCompleted;
    private String label;

    /**
     * Constructs a new task with the specified label, defaults no description.
     * The task is initially marked as not completed.
     *
     * @param label The label of the task.
     */
    public Task(String label) {
        this.label = label;
        this.isCompleted = false;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void changeLabel(String newlabel) {
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

    /**
     * Returns a string representation of a task in file storage format.
     * Format: [TYPE] | status | description | yyyy-MM-dd-yyyy-MM-dd
     * Subclasses should implement this with their respective formats.
     *
     * @return String representation for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns the date to use for sorting this task.
     * Subclasses should implement this to return the relevant date
     * (e.g., due date for Deadline, start date for Event, LocalDate.MAX for Todo).
     *
     * @return The date used for sorting priority.
     */
    protected abstract LocalDate getTaskDate();

    /**
     * Compares this task with another task for ordering by date.
     * Tasks are ordered by their dates, with earlier dates coming first.
     * This enables natural sorting using Collections.sort() or stream.sorted().
     *
     * @param other The task to compare to.
     * @return Negative if this task comes before other, positive if after, zero if equal.
     */
    @Override
    public int compareTo(Task other) {
        return getTaskDate().compareTo(other.getTaskDate());
    }
}
