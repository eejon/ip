package athena.exceptions;

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

    public static AthenaInvalidArguments missingDesciption() {
        return new AthenaInvalidArguments(INVALID_TODO);
    }

    public static AthenaInvalidArguments missingDeadline() {
        return new AthenaInvalidArguments(INVALID_DEADLINE);
    }

    public static AthenaInvalidArguments missingEvent() {
        return new AthenaInvalidArguments(INVALID_EVENT);
    }

}
