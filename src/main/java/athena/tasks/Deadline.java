package athena.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a tasks that need to be done before a specific date/time
 * Subclass of Task
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate dueDate;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param dueDate The deadline date.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        assert dueDate != null : "dueDate should be valid date";
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of deadline task.
     * Format: [D][status_icon] description (by: MMM dd yyyy)
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of this deadline task in file storage format.
     * Format: D | status | description | yyyy-MM-dd
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s | %s",
            isCompleted() ? Task.STATUS_COMPLETE : Task.STATUS_INCOMPLETE,
            getLabel(),
            dueDate.format(INPUT_FORMAT));
    }

    @Override
    protected LocalDate getTaskDate() {
        return this.dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return this.isCompleted() == deadline.isCompleted() &&
                this.getLabel().equals(deadline.getLabel()) &&
                this.dueDate.equals(deadline.dueDate);
    }
}
