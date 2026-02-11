package athena.ui;

public class Response {
    private String message;
    private boolean isSuccess;
    private boolean isError;

    private Response(String message, boolean isError, boolean isSuccess) {
        this.message = message;
        this.isError = isError;
        this.isSuccess = isSuccess;
    }

    public static Response success(String message) {
        return new Response(message, false, true);
    }

    public static Response error(String message) {
        return new Response(message, true, false);
    }

    public static Response standard(String message) {
        return new Response(message, false, false);
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }
}
