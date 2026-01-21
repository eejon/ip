package athena.parser;

import athena.commands.Command;
import athena.commands.CreateCommand;
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

public class Parser {

    public static Command parse(String input) throws AthenaException, NumberFormatException {
        // Parse inputs, split into [command, argument(s)]
        String[] inputs = input.trim().split(" ", 2);
        
        String command = inputs[0].toLowerCase();
        String arguments = inputs.length > 1 ? inputs[1] : ""; // default ""
        
        try {
            switch (command) {
            case "list":
                return new ListCommand();
            
            case "bye":
                return new ExitCommand();
            
            case "mark": 
                return new MarkCommand(Integer.parseInt(arguments) - 1);
            
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(arguments) - 1);
            
            case "todo":
                // No arguments
                if (arguments.isEmpty()) {
                    throw AthenaInvalidArguments.missingDesciption();
                }
                return new CreateCommand(new Todo(arguments));
            case "deadline": {
                String[] args = arguments.split(" /by ");
                // No arguments or no deadline
                if (args.length < 2 || args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
                    throw AthenaInvalidArguments.missingDeadline();
                }
                return new CreateCommand(new Deadline(args[0], args[1]));
            }

            case "event": {
                String[] args = arguments.split(" /from | /to ");
                // No arguments or no from/to
                if (args.length < 3 || args[0].trim().isEmpty() || args[1].trim().isEmpty() || args[2].trim().isEmpty()) {
                    throw AthenaInvalidArguments.missingEvent();
                }
                return new CreateCommand(new Event(args[0], args[1], args[2]));
            }

            default:
                throw AthenaInvalidCommand.invalidCommand();

            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(arguments);
        }
    }
}
