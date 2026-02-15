package athena.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class TaskTest {
    // Test toTaskFormat
    // Test toString
    // test markDone
    // test markNotDone
    @Test
    public void equals_sameTasks_returnTrue() {
        Task task1 = new Todo("read book");
        Task task2 = new Todo("read book");
        assertEquals(task1, task2);

        task1 = new Deadline("return book", LocalDate.of(2026, 2, 16));
        task2 = new Deadline("return book", LocalDate.of(2026, 2, 16));
        assertEquals(task1, task2);

        task1 = new Event("project meeting",
                LocalDate.of(2026, 2, 21), LocalDate.of(2026, 2, 21)
        );
        task2 = new Event("project meeting",
                LocalDate.of(2026, 2, 21), LocalDate.of(2026, 2, 21)
        );
        assertEquals(task1, task2);
    }

    @Test
    public void equals_differentCompletion_returnFalse() {
        Task task1 = new Todo("read book");
        Task task2 = new Todo("read book");
        task1.markDone();
        assertNotEquals(task1, task2);
    }

    @Test
    public void compareTo_deadlineBeforeTodo() {
        Task task1 = new Todo("xyz");
        Task task2 = new Deadline("abc", LocalDate.of(2026, 2, 16));
        assertTrue(task2.compareTo(task1) < 0);
    }

    @Test
    public void markDone_success() {
        Task task = new Todo("read book");
        task.markDone();
        assertTrue(task.isCompleted());
    }

    @Test
    public void markNotDone_success() {
        Task task = new Todo("read book");
        task.markDone();
        task.markNotDone();
        assertTrue(!task.isCompleted());
    }

    @Test
    public void toFileFormat_unmarkedTodo() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
    }
    
    @Test
    public void toFileFormat_markedDeadline() {
        Deadline deadline = new Deadline("return book", LocalDate.of(2026, 2, 15));
        deadline.markDone();
        assertEquals("D | 1 | return book | 2026-02-15", deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_unmarkedEvent() {
        Event event = new Event("project meeting", LocalDate.of(2026, 2, 17), LocalDate.of(2026, 2, 18));
        assertEquals("E | 0 | project meeting | 2026-02-17 - 2026-02-18", event.toFileFormat());
    }

    @Test
    public void toString_unmarkedTask() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
    
    @Test
    public void toString_markedTask() {
        Todo todo = new Todo("read book");
        todo.markDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
