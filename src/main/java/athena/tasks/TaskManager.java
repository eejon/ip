package athena.tasks;

import java.util.List;
import java.util.ArrayList;
/**
 * Handles all Task list operations including adding, modifying 
 * the list and its Tasks. Encapsulates the task list and
 * abstracts the behavior 
 */
public class TaskManager {
    private List<Task> taskList;;

    /**
     * Constructs a new TaskManager object.
     * Encapsulates and manages List<Task>.
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns size of task list.
     * 
     * @return number of tasks in list.
     */
    public int size() {
        return this.taskList.size();
    }
    
    /**
     * Returns task at speicified index.
     * 
     * @param index index of task.
     * @return the task at specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    
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
     * Adds a task to the task list.
     * 
     * @param task specified task to add to list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks task at specified index as complete.
     * 
     * @param index index of task to be marked as complete.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markDone();
    }

    /**
     * Marks tasks at specified index as incomplete.
     * 
     * @param index index of task to be marked as incomplete.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markNotDone();
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param index index of task to be deleted.
     * @throws IndexOutOfBoundsException if given index is out of bounds.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.taskList.remove(index);
    }
}
