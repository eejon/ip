package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

/**
 * Represents a command to mark a task as incompleted.
 * The task is identified by its index in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a new UnmarkCommand object.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * This implementation unmarks a task by referencing the index.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        taskList.unmarkTask(this.index);
        ui.markIncomplete(taskList.get(this.index));
        return 0;
    }
}
