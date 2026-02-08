package athena.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import athena.commands.Command;
import athena.commands.CreateCommand;
import athena.commands.DeleteCommand;
import athena.commands.ExitCommand;
import athena.commands.FindCommand;
import athena.commands.ListCommand;
import athena.commands.MarkCommand;
import athena.commands.UnmarkCommand;
import athena.exceptions.AthenaException;
import athena.exceptions.AthenaInvalidArguments;
import athena.exceptions.AthenaInvalidCommand;
import athena.exceptions.AthenaInvalidDate;
import athena.tasks.Deadline;
import athena.tasks.Event;
import athena.tasks.Todo;

/**
 * Parses user input and maps them to executable Command objects.
 * Contains logic to parsing and interpreting user commands and
 * arguments for supported commands.
 */
public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns command to execute after parsing user input.
     *
     * @param input the user input.
     * @return the command to be executed.
     * @throws AthenaException If user input has invalid commands or insufficient arguments.
     * @throws NumberFormatException If argument is NaN for mark and unmark commands.
     */
    public static Command parse(String input) throws AthenaException, NumberFormatException {
        // Parse inputs, split into [command, argument(s)]
        String[] inputs = input.trim().split(" ", 2);

        String command = inputs[0].toUpperCase();
        String arguments = inputs.length > 1 ? inputs[1] : ""; // default ""

        try {
            CommandType type = CommandType.valueOf(command);
            switch (type) {
            case LIST:
                return new ListCommand();

            case BYE:
                return new ExitCommand();

            case MARK:
                return new MarkCommand(Integer.parseInt(arguments) - 1);

            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(arguments) - 1);

            case TODO:
                return createTodoCommand(arguments);

            case DEADLINE: 
                return createDeadlineCommand(arguments);

            case EVENT:
                return createEventCommand(arguments);

            case DELETE:
                return new DeleteCommand(Integer.parseInt(arguments) - 1);

            case FIND:
                return createFindCommand(arguments);

            default:
                throw AthenaInvalidCommand.invalidCommand();

            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(arguments);
        } catch (IllegalArgumentException e) {
            throw AthenaInvalidCommand.invalidCommand();
        }
    }

    private static Command createTodoCommand(String arguments) throws AthenaException {
        // No arguments
        if (arguments.isEmpty()) {
            throw AthenaInvalidArguments.missingDesciption();
        }
        return new CreateCommand(new Todo(arguments));
    }

    private static Command createDeadlineCommand(String arguments) throws AthenaException {
        String[] args = arguments.split(" /by ");
        // No arguments
        boolean insufficientArgs = args.length < 2;
        if (insufficientArgs) {
            throw AthenaInvalidArguments.missingDeadline();
        }

        // No description or deadline
        boolean noDescription = args[0].trim().isEmpty();
        boolean noDeadline = args[1].trim().isEmpty();
        if (noDescription || noDeadline) {
            throw AthenaInvalidArguments.missingDeadline();
        }
        
        try {
            LocalDate dueDate = LocalDate.parse(args[1].trim(), DATE_FORMAT);
            return new CreateCommand(new Deadline(args[0].trim(), dueDate));
        } catch (DateTimeParseException e) {
            throw AthenaInvalidDate.invalidDate();
        }
    }

    private static Command createEventCommand(String arguments) throws AthenaException {
        String[] args = arguments.split(" /from | /to ");
        // No arguments
        boolean insufficientArgs = args.length < 3;
        if (insufficientArgs) {
            throw AthenaInvalidArguments.missingEvent();
        }

        // No description/from/to
        boolean noDescription = args[0].trim().isEmpty();
        boolean noFromDate = args[1].trim().isEmpty();
        boolean noToDate = args[2].trim().isEmpty();
        if (noDescription || noFromDate || noToDate) {
            throw AthenaInvalidArguments.missingEvent();
        }

        try {
            LocalDate from = LocalDate.parse(args[1].trim(), DATE_FORMAT);
            LocalDate to = LocalDate.parse(args[2].trim(), DATE_FORMAT);
            return new CreateCommand(new Event(args[0].trim(), from, to));
        } catch (DateTimeParseException e) {
            throw AthenaInvalidDate.invalidDate();
        }
    }

    private static Command createFindCommand(String arguments) throws AthenaException {
        if (arguments.isEmpty()) {
            throw AthenaInvalidArguments.missingKeyword();
        }
        return new FindCommand(arguments.trim());
    }
}
