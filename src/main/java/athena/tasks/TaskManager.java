package athena.tasks;

import java.util.List;

import athena.storage.TaskStorage;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Handles all Task list operations including adding, modifying 
 * the list and its Tasks. Encapsulates the task list and
 * abstracts the behavior 
 */
public class TaskManager {
    private List<Task> taskList;
    private TaskStorage storage;

    /**
     * Constructs a new TaskManager object.
     * Encapsulates and manages List<Task>.
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
        this.storage = new TaskStorage();
    }

    /**
     * Constructs a new TaskManager object with a custom TaskStorage.
     * Encapsulates and manages List<Task> with the provided storage.
     *
     * @param storage The TaskStorage instance to use for persistence.
     */
    public TaskManager(TaskStorage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Loads tasks from storage into the task list.
     *
     * @throws IOException if an I/O error occurs during loading.
     */
    public void loadTasks() throws IOException {
        this.taskList = storage.loadTasks();
    }

    private void saveTasks() {
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }
    
    /**
     * Returns task at specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Displays a numbered list of tasks with their current status.
     *
     * @return A formatted string containing all tasks or a message if the list is empty.
     */
    public String iterateList() {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() == 0) {
            sb.append("\t The field is clear. Victory is absolute.\n");
            return sb.toString();
        }
        sb.append("\t Your campaign stands as follows:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("\t %d.%s\n", i + 1, taskList.get(i)));
        }
        return sb.toString();
    }

    /**
     * Adds a task to the task list and saves to storage.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        saveTasks();
    }

    /**
     * Marks task at specified index as complete and saves to storage.
     *
     * @param index index of task to be marked as complete.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markDone();
        this.saveTasks();
    }

    /**
     * Marks tasks at specified index as incomplete and saves to storage.
     *
     * @param index index of task to be marked as incomplete.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markNotDone();
        this.saveTasks();
    }

    /**
     * Deletes a task from the task list and saves to storage.
     *
     * @param index index of task to be deleted.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.taskList.remove(index);
        this.saveTasks();
    }

    /**
     * Finds all tasks that contain the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Task task : taskList) {
            if (task.getlabel().toLowerCase().contains(lowerKeyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
