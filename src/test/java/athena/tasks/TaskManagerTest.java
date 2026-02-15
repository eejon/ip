package athena.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import athena.storage.TaskStorage;

public class TaskManagerTest {
    // Test Double
    private static class FakeStorage extends TaskStorage {
        private List<Task> savedTasks = new ArrayList<>();
        private boolean canSave = true;

        @Override
        public void saveTasks(List<Task> data) throws IOException {
            if (!canSave) {
                throw new IOException("save failed");
            }
            savedTasks = new ArrayList<>(data);
        }

        @Override
        public List<Task> loadTasks() throws IOException {
            return savedTasks;
        }
    }

    @Test
    public void addTask_addsAndPersists() throws IOException {
        FakeStorage storage = new FakeStorage();
        TaskManager taskManager = new TaskManager(storage);

        Task todo = new Todo("read book");
        Task deadline = new Deadline("return book",
                LocalDate.of(2026, 2, 15)
        );
        Task event = new Event("project meeting",
                LocalDate.of(2026, 2, 21),
                LocalDate.of(2026, 2, 21)
        );

        taskManager.addTask(todo);
        assertEquals(1, taskManager.size());
        assertEquals(1, storage.loadTasks().size());
        assertEquals("read book", taskManager.get(0).getLabel());
        assertEquals("read book", storage.loadTasks().get(0).getLabel());

        taskManager.addTask(deadline);
        assertEquals(2, taskManager.size());
        assertEquals(2, storage.loadTasks().size());
        assertEquals("return book", taskManager.get(1).getLabel());
        assertEquals("return book", storage.loadTasks().get(1).getLabel());

        taskManager.addTask(event);
        assertEquals(3, taskManager.size());
        assertEquals(3, storage.loadTasks().size());
        assertEquals("project meeting", taskManager.get(2).getLabel());
        assertEquals("project meeting", storage.loadTasks().get(2).getLabel());
    }

    @Test
    public void markTask_taskIsMarkedAndPersisted() throws IOException {
        FakeStorage storage = new FakeStorage();
        TaskManager taskManager = new TaskManager(storage);

        taskManager.addTask(new Todo("read book"));
        taskManager.markTask(0);
        assertTrue(taskManager.get(0).isCompleted());
        assertTrue(storage.loadTasks().get(0).isCompleted());
    }

    @Test
    public void unmarkTask_taskIsNotMarkedAndPersisted() throws IOException {
        FakeStorage storage = new FakeStorage();
        TaskManager taskManager = new TaskManager(storage);

        taskManager.addTask(new Todo("read book"));
        taskManager.markTask(0);
        taskManager.unmarkTask(0);
        assertTrue(!taskManager.get(0).isCompleted());
        assertTrue(!storage.loadTasks().get(0).isCompleted());
    }

    @Test
    public void deleteTask_taskIsDeletedFromStateAndStorage() throws IOException {
        FakeStorage storage = new FakeStorage();
        TaskManager taskManager = new TaskManager(storage);

        taskManager.addTask(new Todo("read book"));
        taskManager.deleteTask(0);
        assertEquals(0, taskManager.size());
        assertTrue(storage.loadTasks().isEmpty());
    }

    @Test
    public void findByKeyword_correctTask() {
        TaskManager taskManager = new TaskManager();
        List<Task> expectedList = new ArrayList<>();

        Task task1 = new Todo("read book");
        Task task2 = new Todo("prepare slides");
        Task task3 = new Todo("read docs");
        Task[] tasks = new Task[]{ task1, task2, task3 };
        for (Task task : tasks) {
            taskManager.addTask(task);
            if (task.getLabel().contains("read")) {
                expectedList.add(task);
            }
        }

        List<Task> actualList = taskManager.findByKeyword("read");
        assertEquals(expectedList, actualList);
    }

    @Test
    public void loadTasks_loadsFromStorage() throws IOException {
        FakeStorage storage = new FakeStorage();
        TaskManager manager1 = new TaskManager(storage);

        manager1.addTask(new Todo("task 1"));
        manager1.addTask(new Todo("task 2"));

        // Create new manager with same storage
        TaskManager manager2 = new TaskManager(storage);
        manager2.loadTasks();

        assertEquals(2, manager2.size());
        assertEquals("task 1", manager2.get(0).getLabel());
        assertEquals("task 2", manager2.get(1).getLabel());
    }

    @Test
    public void sort_sortsByDatePriority() {
        TaskManager taskManager = new TaskManager();

        Task todo = new Todo("todo task");
        Task deadline1 = new Deadline("due soon", LocalDate.of(2026, 2, 20));
        Task deadline2 = new Deadline("due later", LocalDate.of(2026, 3, 15));
        Task event = new Event("meeting",
                LocalDate.of(2026, 2, 18),
                LocalDate.of(2026, 2, 18));

        taskManager.addTask(todo);
        taskManager.addTask(deadline2);
        taskManager.addTask(deadline1);
        taskManager.addTask(event);

        List<Task> sorted = taskManager.sort();

        assertEquals("meeting", sorted.get(0).getLabel());
        assertEquals("due soon", sorted.get(1).getLabel());
        assertEquals("due later", sorted.get(2).getLabel());
        assertEquals("todo task", sorted.get(3).getLabel());
    }

    @Test
    public void getTasks_returnsDefensiveCopy() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo("task 1"));

        List<Task> tasks = taskManager.getTasks();
        tasks.clear();

        assertEquals(1, taskManager.size());
    }

    @Test
    public void findByKeyword_emptyKeyword_returnsAll() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo("task 1"));
        taskManager.addTask(new Todo("task 2"));

        List<Task> result = taskManager.findByKeyword("");
        assertEquals(2, result.size());
    }

    @Test
    public void findByKeyword_caseInsensitive() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo("READ book"));
        taskManager.addTask(new Todo("read DOCS"));

        List<Task> result = taskManager.findByKeyword("ReAd");
        assertEquals(2, result.size());
    }

    @Test
    public void findByKeyword_noMatches_returnsEmpty() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo("task 1"));

        List<Task> result = taskManager.findByKeyword("xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    public void findByKeyword_emptyList_returnsEmpty() {
        TaskManager taskManager = new TaskManager();
        List<Task> result = taskManager.findByKeyword("test");
        assertTrue(result.isEmpty());
    }

    @Test
    public void addTask_saveFailure_handlesGracefully() {
        FakeStorage storage = new FakeStorage();
        storage.canSave = false;
        TaskManager taskManager = new TaskManager(storage);

        taskManager.addTask(new Todo("task"));
        assertEquals(1, taskManager.size());
    }
}
