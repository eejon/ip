package athena.commands;

import java.util.List;

import athena.tasks.Task;
import athena.tasks.TaskManager;
import athena.ui.UI;

/**
 * Represents a command to find tasks by searching for a keyword in the description.
 * This command searches through all tasks and returns those containing the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     * This implementation finds and displays tasks containing the search keyword.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int dispatch(TaskManager taskList, UI ui) {
        List<Task> foundTasks = taskList.findByKeyword(keyword);
        ui.showFoundTasks(foundTasks, keyword);
        return 0;
    }
}
