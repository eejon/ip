package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

/**
 * Represents a command to mark a task as completed.
 * The task is identified by its index in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a new MarkCommand object.
     *
     * @param index The index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }


    /**
     * {@inheritDoc}
     * This implementation marks a task by referencing the index.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, UI ui) throws IndexOutOfBoundsException {
        taskList.markTask(this.index);
        ui.markComplete(taskList.get(this.index));
        return 0;
    }
}
