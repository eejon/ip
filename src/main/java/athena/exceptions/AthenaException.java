package athena.exceptions;
/**
 * Represents the base exception class for all Athena chatbot -specific exceptions.
 * This exception is thrown when errors occur during command processing or
 * user interaction with the Athena chatbot.
 */
public class AthenaException extends Exception {
    /**
     * Constructs a new AthenaException with the specified error message.
     *
     * @param message The error message explaining what went wrong.
     */
    public AthenaException(String message) {
        super(message);
    }
}
