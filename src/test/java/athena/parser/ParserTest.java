package athena.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import athena.commands.Command;
import athena.commands.CreateCommand;
import athena.commands.DeleteCommand;
import athena.commands.ExitCommand;
import athena.commands.ListCommand;
import athena.commands.MarkCommand;
import athena.commands.UnmarkCommand;
import athena.exceptions.AthenaException;

public class ParserTest {

    @Test
    public void parse_listCommand_success() throws AthenaException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand, "Should return a ListCommand instance");
    }

    @Test
    public void parse_byeCommand_success() throws AthenaException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand, "Should return an ExitCommand instance");
    }

    @Test
    public void parse_todoCommand_success() throws AthenaException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof CreateCommand, "Should return a CreateCommand instance");
    }

    // https://www.baeldung.com/junit-assert-exception#1-assert-an-exception-is-thrown
    @Test
    public void parse_todoCommandNoDescription_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("todo");
        });
        String expectedMessage = "\t Strategy requires detail. A todo must have a defined objective.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }

    @Test
    public void parse_deadlineCommand_success() throws AthenaException {
        Command command = Parser.parse("deadline return book /by 2019-12-02");
        assertTrue(command instanceof CreateCommand, "Should return a CreateCommand instance");
    }

    @Test
    public void parse_deadlineCommandNoDate_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("deadline return book /by");
        });

        String expectedMessage = "\t Strategy requires detail. A deadline must have a defined objective.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }

    @Test
    public void parse_deadlineCommandInvalidDate_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("deadline return book /by 1 Jan");
        });

        String expectedMessage = "\t Chronos rejects this entry. Chronos enforces yyyy-MM-dd.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }

    @Test
    public void parse_eventCommand_success() throws AthenaException {
        Command command = Parser.parse("event meeting /from 2019-12-02 /to 2019-12-03");
        assertTrue(command instanceof CreateCommand, "Should return a CreateCommand instance");
    }

    @Test
    public void parse_eventCommandMissingTo_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("event meeting /from 2019-12-02 /to");
        });

        String expectedMessage = "\t Strategy requires detail. An event must have a defined objective.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }

    @Test
    public void parse_eventCommandInvalidDate_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("event meeting /from 2019-12-02 /to 31 jan");
        });

        String expectedMessage = "\t Chronos rejects this entry. Chronos enforces yyyy-MM-dd.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }

    @Test
    public void parse_markCommand_success() throws AthenaException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand, "Should return a MarkCommand instance");
    }

    @Test
    public void parse_unmarkCommand_success() throws AthenaException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand, "Should return an UnmarkCommand instance");
    }

    @Test
    public void parse_deleteCommand_success() throws AthenaException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand, "Should return a DeleteCommand instance");
    }

    @Test
    public void parse_invalidCommand_throwsException() {
        Exception exception = assertThrows(AthenaException.class, () -> {
            Parser.parse("hi");
        });

        String expectedMessage = "\t I do not recognize that tactic. Speak with clarity.";
        String actual = exception.getMessage();

        assertEquals(expectedMessage, actual);
    }
}
