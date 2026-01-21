package athena.tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> taskList;;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return this.taskList.size();
    }
    
    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markDone();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markNotDone();
    }
}
