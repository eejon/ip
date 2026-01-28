package athena.exceptions;
/**
 * Represents an exception for invalid or missing command arguments.
 * This exception is thrown when a command is missing required parameters
 * or when the provided arguments are in an incorrect format.
 */
public class AthenaInvalidArguments extends AthenaException {

    /* Class level constant error messages */
    private static final String INVALID_TODO = 
        "\t Strategy requires detail. A todo must have a defined objective.";
    private static final String INVALID_DEADLINE = 
        "\t Strategy requires detail. A deadline must have a defined objective.";
    private static final String INVALID_EVENT = 
        "\t Strategy requires detail. An event must have a defined objective.";

    private AthenaInvalidArguments(String message) {
        super(message);
    }

    /**
     * Factory method to create AthenaInvalidArguments.
     * 
     * @return AthenaInvalidArguments exception with invalid todo error message.
     */
    public static AthenaInvalidArguments missingDesciption() {
        return new AthenaInvalidArguments(INVALID_TODO);
    }

    /**
     * Factory method to create AthenaInvalidArguments.
     * 
     * @return AthenaInvalidArguments exception with invalid deadline error message.
     */
    public static AthenaInvalidArguments missingDeadline() {
        return new AthenaInvalidArguments(INVALID_DEADLINE);
    }

    /**
     * Factory method to create AthenaInvalidArguments.
     * 
     * @return AthenaInvalidArguments exception with invalid event error message.
     */
    public static AthenaInvalidArguments missingEvent() {
        return new AthenaInvalidArguments(INVALID_EVENT);
    }

}
