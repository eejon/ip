package athena.exceptions;

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
