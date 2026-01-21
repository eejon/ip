package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public int dispatch(TaskManager taskList, UI ui) throws IndexOutOfBoundsException {
        taskList.markTask(this.index);
        ui.markComplete(taskList.get(this.index));
        return 0;
    }
}
