package athena.commands;

import athena.ui.UI;
import athena.tasks.Task;
import athena.tasks.TaskManager;

public class CreateCommand extends Command {
    private final Task task; // 1 task per command, cannot be changed

    public CreateCommand(Task task) {
        this.task = task;
    }

    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        try {
            taskList.addTask(this.task); // TaskManager adds task
            ui.taskCreated(task, taskList.size()); // UI prints task created message
            return 0;
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundsError();
            return -1;
        }
    }
}
