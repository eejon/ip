package athena.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that start at a specific date/time and ends at a
 * specific date/time. Subclass of Task.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event.
     * @param from The start date.
     * @param to The end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of event task.
     * Format: [E][status_icon] description (from: MMM dd yyyy to: MMM dd yyyy)
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string representation of this event task in file storage format.
     * Format: E | status | description | yyyy-MM-dd-yyyy-MM-dd
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s - %s",
            getStatus() ? "1" : "0",
            getlabel(),
            from.format(INPUT_FORMAT),
            to.format(INPUT_FORMAT));
    }
    
}
