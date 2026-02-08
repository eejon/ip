package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.Ui;

/**
 * Represents a command to display tasks sorted by priority.
 * Tasks are sorted by their dates (deadlines/events), with earlier dates first.
 * Tasks without dates (Todos) are sorted to the end.
 */
public class SortCommand extends Command {

    /**
     * {@inheritDoc}
     * This implementation displays tasks sorted by priority (date).
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, Ui ui) {
        ui.showTaskList(taskList.iterateSortedList());
        return STATUS_CODE_SUCCESS;
    }
}
