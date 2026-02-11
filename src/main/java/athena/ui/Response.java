package athena.ui;

/**
 * Encapsulates UI messages with status indicators.
 * Provides a type-safe way to represent responses with error, success, or standard status.
 */
public class Response {
    private String message;
    private boolean isSuccess;
    private boolean isError;

    private Response(String message, boolean isError, boolean isSuccess) {
        this.message = message;
        this.isError = isError;
        this.isSuccess = isSuccess;
    }

    /**
     * Creates a success response.
     *
     * @param message The success message to display.
     * @return A Response object marked as success.
     */
    public static Response success(String message) {
        return new Response(message, false, true);
    }

    /**
     * Creates an error response.
     *
     * @param message The error message to display.
     * @return A Response object marked as error.
     */
    public static Response error(String message) {
        return new Response(message, true, false);
    }

    /**
     * Creates a standard response (neither error nor success).
     *
     * @param message The message to display.
     * @return A Response object with standard status.
     */
    public static Response standard(String message) {
        return new Response(message, false, false);
    }

    /**
     * Gets the message content.
     *
     * @return The message string.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Checks if this response represents an error.
     *
     * @return true if this is an error response, false otherwise.
     */
    public boolean isError() {
        return this.isError;
    }

    /**
     * Checks if this response represents a success.
     *
     * @return true if this is a success response, false otherwise.
     */
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
