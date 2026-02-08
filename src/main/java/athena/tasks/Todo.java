package athena.tasks;

import java.time.LocalDate;
/**
 * Represents a todo task without any date/time attached to it
 * Subclass of Task
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of todo task.
     * Format: [T][status_icon] description
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of this todo task in file storage format.
     * Format: T | status | description
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %s | %s",
            isCompleted() ? Task.STATUS_COMPLETE : Task.STATUS_INCOMPLETE,
            getLabel());
    }

    @Override
    protected LocalDate getTaskDate() {
        return LocalDate.MAX;
    }
}
