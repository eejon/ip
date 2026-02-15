package athena.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that start at a specific date/time and ends at a
 * specific date/time. Subclass of Task.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event.
     * @param from The start date.
     * @param to The end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        assert from != null && to != null : "to and from should be valid local dates";
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
     * {@inheritDoc}
     * Returns a string representation of this event task in file storage format.
     * Format: E | status | description | yyyy-MM-dd-yyyy-MM-dd
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s - %s",
            isCompleted() ? Task.STATUS_COMPLETE : Task.STATUS_INCOMPLETE,
            getLabel(),
            from.format(INPUT_FORMAT),
            to.format(INPUT_FORMAT));
    }

    @Override
    protected LocalDate getTaskDate() {
        return this.to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return this.isCompleted() == event.isCompleted()
                && this.getLabel().equals(event.getLabel())
                && this.from.equals(event.from)
                && this.to.equals(event.to);
    }
}
