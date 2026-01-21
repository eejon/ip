package athena.commands;

import athena.ui.UI;
import athena.tasks.TaskManager;

/**
 * Represents the command to exit the Athena chatbot application.
 */
public class ExitCommand extends Command {

	  /**
     * {@inheritDoc}
     * This implementation facilitates printing of the exit message.
     * 
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return returns exit code 1 to signal termination to main loop.
     */
    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        ui.printExit();
        return 1;
    }
}
