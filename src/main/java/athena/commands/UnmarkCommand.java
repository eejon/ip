package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        taskList.unmarkTask(this.index);
        ui.markIncomplete(taskList.get(this.index));
        return 0;
    }
}