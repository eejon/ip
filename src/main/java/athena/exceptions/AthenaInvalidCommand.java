package athena.exceptions;
/**
 * Represents an exception for unrecognized or invalid commands.
 * This exception is thrown when the user enters a command that is not
 * recognized by the Athena chatbot.
 */
public class AthenaInvalidCommand extends AthenaException {
    /* Class level constant error messages */
    private static final String INVALID_COMMAND =
        "\t I do not recognize that tactic. Speak with clarity.";

    private AthenaInvalidCommand(String message) {
        super(message);
    }

    /**
     * Factory method to create AthenaInvalidCommand.
     *
     * @return AthenaInvalidCommand exception with invalid command error message.
     */
    public static AthenaInvalidCommand invalidCommand() {
        return new AthenaInvalidCommand(INVALID_COMMAND);
    }
}
