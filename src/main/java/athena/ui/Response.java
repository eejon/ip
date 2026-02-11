package athena.ui;

public class Response {
    private String message;
    private boolean isError;

    private Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public static Response success(String message) {
        return new Response(message, false);
    }

    public static Response error(String message) {
        return new Response(message, true);
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }
}
