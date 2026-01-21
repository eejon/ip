package athena.commands;

import athena.tasks.TaskManager;
import athena.ui.UI;

public abstract class Command {
    // Every command needs:
    // TaskList - to keep track of tasks for runnign commands
    // UI - to print details
    
    public abstract int dispatch(TaskManager taskList, UI ui);


}
