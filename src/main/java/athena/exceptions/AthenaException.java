package athena.exceptions;
/**
 * Represents exceptions specific to the Athena.
 * Used to handle errors such as incorrect user input
 */
public class AthenaException extends Exception {
    
    /**
     * Creates a new AthenaException with the specified error message.
     *
     * @param message The error message explaining what went wrong.
     */
    public AthenaException(String message) {
        super(message);
    }
}
