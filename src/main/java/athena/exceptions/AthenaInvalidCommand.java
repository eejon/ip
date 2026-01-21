package athena.exceptions;

public class AthenaInvalidCommand extends AthenaException {
    /* Class level constant error messages */
    private static final String INVALID_COMMAND = 
    "\t I do not recognize that tactic. Speak with clarity.";

    private AthenaInvalidCommand(String message) {
        super(message);
    }

    public static AthenaInvalidCommand invalidCommand() {
        return new AthenaInvalidCommand(INVALID_COMMAND);
    }
}
