package athena.commands;

import athena.ui.UI;
import athena.tasks.TaskManager;

public class ExitCommand extends Command {

    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        ui.printExit();
        return 1;
    }
}
