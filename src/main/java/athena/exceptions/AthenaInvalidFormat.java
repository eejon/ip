package athena.exceptions;
/**
 * Represents an exception for invalid input formats.
 * This exception is thrown when the user enters an invalid or unsupported
 * format recognized by the Athena chatbot.
 */
public class AthenaInvalidFormat extends AthenaException {
    /* Class level constant error messages */
    private static final String INVALID_FORMAT =
        "\t Incomplete Offering: the meager record lacks necessary components";
    private static final String INVALID_FORMAT_TODO =
        "\t This todo task is a cipher I cannot solve. Correct the format.";
    private static final String INVALID_FORMAT_DEADLINE =
        "\t This deadline task is a cipher I cannot solve. Correct the format.";
    private static final String INVALID_FORMAT_EVENT =
        "\t This event task is a cipher I cannot solve. Correct the format.";

    private AthenaInvalidFormat(String message) {
        super(message);
    }

    /**
     * Factory method to create AthenaInvalidFormat.
     *
     * @return AthenaInvalidFormat exception with invalid format error message.
     */
    public static AthenaInvalidFormat invalidFormat() {
        return new AthenaInvalidFormat(INVALID_FORMAT);
    }

    /**
     * Factory method to create AthenaInvalidFormat for Todo.
     *
     * @return AthenaInvalidFormat exception with invalid todo format error message.
     */
    public static AthenaInvalidFormat invalidTodoFormat() {
        return new AthenaInvalidFormat(INVALID_FORMAT_TODO);
    }

    /**
     * Factory method to create AthenaInvalidFormat for Deadline.
     *
     * @return AthenaInvalidFormat exception with invalid deadline format error message.
     */
    public static AthenaInvalidFormat invalidDeadlineFormat() {
        return new AthenaInvalidFormat(INVALID_FORMAT_DEADLINE);
    }

    /**
     * Factory method to create AthenaInvalidFormat for Event.
     *
     * @return AthenaInvalidFormat exception with invalid event format error message.
     */
    public static AthenaInvalidFormat invalidEventFormat() {
        return new AthenaInvalidFormat(INVALID_FORMAT_EVENT);
    }

}
