package it.eomm.plugins.dolm.exceptions;

public class PipeException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PipeException() {
        super();
    }

    public PipeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PipeException(String message) {
        super(message);
    }

    public PipeException(Throwable cause) {
        super(cause);
    }

}
