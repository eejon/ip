package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

public class ListCommand extends Command {

    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        ui.showTaskList(taskList);
        return 0;
    }
}
