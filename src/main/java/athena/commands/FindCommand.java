package athena.commands;

import java.util.List;

import athena.tasks.Task;
import athena.tasks.TaskManager;
import athena.ui.UI;


public class FindCommand extends Command {
    private final String keyword;


    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        List<Task> foundTasks = taskList.findByKeyword(keyword);
        ui.showFoundTasks(foundTasks, keyword);
        return 0;
    }
}
