package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.Ui;

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
    public int dispatch(TaskManager taskList, Ui ui) {
        ui.printExit();
        return Command.EXIT_STATUS_CODE;
    }
}
