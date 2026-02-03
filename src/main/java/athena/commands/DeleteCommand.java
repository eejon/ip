package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

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
    public int dispatch(TaskManager taskList, UI ui) throws IndexOutOfBoundsException {
        ui.showDeleted(taskList.get(this.index), taskList.size() - 1);
        taskList.deleteTask(this.index);
        return 0;
    }
}
