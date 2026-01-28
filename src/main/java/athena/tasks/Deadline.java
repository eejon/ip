package athena.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a tasks that need to be done before a specific date/time
 * Subclass of Task
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param dueDate The deadline date.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
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
     * Returns a string representation of this deadline task in file storage format.
     * Format: D | status | description | yyyy-MM-dd
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s | %s",
            getStatus() ? "1" : "0",
            getlabel(),
            dueDate.format(INPUT_FORMAT));
    }

}
