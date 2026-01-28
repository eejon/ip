package athena.tasks;
/**
 * Represents a tasks that need to be done before a specific date/time
 * Subclass of Task
 */
public class Deadline extends Task {
    protected String duedate;

	/**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param duedate The deadline date/time.
     */
    public Deadline(String description, String duedate) {
        super(description);
        this.duedate = duedate;
    }

    /**
     * Returns a string representation of deadline task.
     * Format: [D][status_icon] description (by: deadline)
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + duedate + ")";
    }

    /**
     * Returns a string representation of this deadline task in file storage format.
     * Format: D | status | description | duedate
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s | %s",
            getStatus() ? "1" : "0",
            getlabel(),
            duedate);
    }

}
