package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.Ui;

/**
 * Represents an abstract command in the Athena chatbot.
 * This is the base class for all commands.
 */
public abstract class Command {
    public static final int SUCCESS_STATUS_CODE = 0;
    public static final int EXIT_STATUS_CODE = 1;
    /**
     * Returns the exit code after the command executes.
     * Uses the UI and TaskManager to handle changes to UI and List of tasks.
     *
     * @param taskList The TaskManager object to be acted on.
     * @param ui The UI object to print messages for each command.
     * @return exit code 0 for success.
     */
    public abstract int dispatch(TaskManager taskList, Ui ui);


}
