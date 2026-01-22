package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

/**
 * Represents a command to display all tasks in the task list.
 * This command retrieves the current task list and delegates to the UI
 * for formatting and display.
 */
public class ListCommand extends Command {

	  /**
     * {@inheritDoc}
     * This implementation facilitates printing of the items in the task list.
     * 
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        ui.showTaskList(taskList.iterateList());
        return 0;
    }
}
