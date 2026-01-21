/**
 * Represents a task with a name, description, and completion status.
 * A task can be marked as complete or incomplete.
 */
public class Task {
	private boolean isCompleted;
	private String name;
	private String description;

	/**
	 * Creates a new task with the specified name, defaults no description.
	 * The task is initially marked as not completed.
	 *
	 * @param name The name of the task.
	 */
	public Task(String name) {
		this.name = name;
		this.description = "";
		this.isCompleted = false;
	}

	/**
	 * Creates a new task with the specified name and description.
	 * The task is initially marked as not completed.
	 *
	 * @param name The name of the task.
	 * @param description The description of the task.
	 */
	public Task(String name, String description) {
		this.name = name;
		this.description = description;
		this.isCompleted = false;
	}

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.description;
	}

	public boolean getStatus() {
		return this.isCompleted;
	}

	public void changeName(String newName) {
		this.name = newName;
	}

	public void editDesc(String body) {
		this.description = body;
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
		return "[" + getStatusIcon() + "] " + name;
	}
	

}
