package athena.tasks;
/**
 * Represents a task that start at a specific date/time and ends at a 
 * specific date/time. Subclass of Task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event.
     * @param from The start date/time.
     * @param to The end date/time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of event task.
     * Format: [E][status_icon] description (from: start to: end)
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of this event task in file storage format.
     * Format: E | status | description | from-to
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s-%s",
            getStatus() ? "1" : "0",
            getlabel(),
            from,
            to);
    }
    
}
