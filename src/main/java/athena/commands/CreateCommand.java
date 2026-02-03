package athena.commands;

import athena.tasks.Task;
import athena.tasks.TaskManager;
import athena.ui.Ui;

/**
 * Represents a command to create and add a new task to the task list.
 */
public class CreateCommand extends Command {
    private Task task; // 1 task per command, cannot be changed

    /**
     * Constructs a new CreateCommand object.
     *
     * @param task The task to create.
     */
    public CreateCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * This implementation adds the task associated with command to the task list.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, Ui ui) {
        taskList.addTask(this.task); // TaskManager adds task
        ui.taskCreated(task, taskList.size()); // UI prints task created message
        return 0;
    }
}
