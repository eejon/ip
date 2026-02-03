package athena.exceptions;
/**
 * Represents an exception for invalid date formats.
 * This exception is thrown when the user enters an invalid or unsupported date
 * format recognized by the Athena chatbot.
 */
public class AthenaInvalidDate extends AthenaException {
    /* Class level constant error messages */
    private static final String INVALID_DATE =
        "\t Chronos rejects this entry. Chronos enforces yyyy-MM-dd.";

    private AthenaInvalidDate(String message) {
        super(message);
    }

    /**
     * Factory method to create AthenaInvalidDate.
     *
     * @return AthenaInvalidDate exception with invalid date error message.
     */
    public static AthenaInvalidDate invalidDate() {
        return new AthenaInvalidDate(INVALID_DATE);
    }
}
