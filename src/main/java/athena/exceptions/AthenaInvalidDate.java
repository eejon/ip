package athena.exceptions;
/**
 * Represents an exception for invalid date formats.
 * This exception is thrown when the user enters an invalid or unsupported date
 * format recognized by the Athena chatbot.
 */
public class AthenaInvalidDate extends AthenaException {
    /* Class level constant error messages */
    private static final String INVALID_DATE =
        "\t The fates do not recognize this date. Speak in the tongue of yyyy-MM-dd.";
    private static final String FROM_DATE_GREATER_THAN_TO_DATE =
        "\t Wisdom dictates that one cannot finish before they have begun. Adjust your date range accordingly.";

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

    public static AthenaInvalidDate invalidDateRange() {
        return new AthenaInvalidDate(FROM_DATE_GREATER_THAN_TO_DATE);
    }
}
