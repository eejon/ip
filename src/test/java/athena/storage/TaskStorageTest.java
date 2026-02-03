package athena.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import athena.tasks.Deadline;
import athena.tasks.Event;
import athena.tasks.Task;
import athena.tasks.Todo;

public class TaskStorageTest {

    // https://www.geeksforgeeks.org/software-testing/junit-5-tempdir/
    @TempDir
    Path tempDir;

    @Test
    public void saveTasks_writesExpectedFormat() throws IOException {
        Path filePath = tempDir.resolve("test.txt");
        TaskStorage storage = new TaskStorage(filePath.toString());

        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", LocalDate.of(2026, 1, 31));
        Event event = new Event("project meeting",
            LocalDate.of(2026, 1, 30), LocalDate.of(2026, 1, 31));
        deadline.markDone();

        storage.saveTasks(Arrays.asList(todo, deadline, event));

        String content = Files.readString(filePath);
        String expected = String.join("\n",
            "T | 0 | read book",
            "D | 1 | return book | 2026-01-31",
            "E | 0 | project meeting | 2026-01-30 - 2026-01-31");
        assertEquals(expected, content, "Should return same content");
    }

    @Test
    public void loadTasks_success() throws IOException {
        Path filePath = tempDir.resolve("test.txt");
        TaskStorage storage = new TaskStorage(filePath.toString());

        String data = String.join("\n",
            "T | 1 | read book",
            "D | 1 | return book | 2026-01-31",
            "E | 0 | project meeting | 2026-01-30 - 2026-01-31");
        Files.writeString(filePath, data);

        List<Task> tasks = storage.loadTasks();
        assertEquals(3, tasks.size(), "Should return same number of tasks");
        assertTrue(tasks.get(0) instanceof Todo, "Should return a Todo object");
        assertTrue(tasks.get(1) instanceof Deadline, "Should return a Deadline object");
        assertTrue(tasks.get(2) instanceof Event, "Should return an Event object");

        assertEquals("T | 1 | read book", tasks.get(0).toFileFormat(), "Should return same task entry");
        assertEquals("D | 1 | return book | 2026-01-31", tasks.get(1).toFileFormat(), "Should return same task entry");
        assertEquals("E | 0 | project meeting | 2026-01-30 - 2026-01-31", tasks.get(2).toFileFormat(),
            "Should return same task entry");
    }
}
