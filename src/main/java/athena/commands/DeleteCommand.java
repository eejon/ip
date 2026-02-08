package athena.commands;

import athena.tasks.Task;
import athena.tasks.TaskManager;
import athena.ui.Ui;

/**
 * Represents a command to create and add a new task to the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * This implementation deletes the task at index from the task list.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, Ui ui) throws IndexOutOfBoundsException {
        Task deleted = taskList.get(this.index);
        taskList.deleteTask(this.index);
        ui.showDeleted(deleted, taskList.size() - 1);
        return Command.SUCCESS_STATUS_CODE;
    }
}
