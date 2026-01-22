package athena.parser;

import athena.commands.Command;
import athena.commands.CreateCommand;
import athena.commands.DeleteCommand;
import athena.commands.ExitCommand;
import athena.commands.ListCommand;
import athena.commands.MarkCommand;
import athena.commands.UnmarkCommand;
import athena.exceptions.AthenaException;
import athena.exceptions.AthenaInvalidArguments;
import athena.exceptions.AthenaInvalidCommand;
import athena.tasks.Deadline;
import athena.tasks.Event;
import athena.tasks.Todo;

/**
 * Parses user input and maps them to executable Command objects.
 * Contains logic to parsing and interpreting user commands and 
 * arguments for supported commands.
 */
public class Parser {

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
        
        CommandType type = CommandType.valueOf(command);
        try {
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
                // No arguments
                if (arguments.isEmpty()) {
                    throw AthenaInvalidArguments.missingDesciption();
                }
                return new CreateCommand(new Todo(arguments));
            case DEADLINE: {
                String[] args = arguments.split(" /by ");
                // No arguments or no deadline
                if (args.length < 2 || args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
                    throw AthenaInvalidArguments.missingDeadline();
                }
                return new CreateCommand(new Deadline(args[0], args[1]));
            }

            case EVENT: {
                String[] args = arguments.split(" /from | /to ");
                // No arguments or no from/to
                if (args.length < 3 || args[0].trim().isEmpty() || args[1].trim().isEmpty() 
                	|| args[2].trim().isEmpty()) {
                    throw AthenaInvalidArguments.missingEvent();
                }
                return new CreateCommand(new Event(args[0], args[1], args[2]));
            }

            case DELETE:
                return new DeleteCommand(Integer.parseInt(arguments) - 1);

            default:
                throw AthenaInvalidCommand.invalidCommand();

            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(arguments);
        }
    }
}
