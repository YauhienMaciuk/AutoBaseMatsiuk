package com.epam.autobasematsiuk.exception;

/**
 * The class CommandException.
 */
public class CommandException extends Exception {

    private static final long serialVersionUID = 4601583141769915461L;

    /**
     * Instantiates a new technical exception.
     */
    public CommandException() {
        super();
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     * @param cause   is the cause
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param cause is the cause
     */
    public CommandException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message            is the message
     * @param cause              is the cause
     * @param enableSuppression  is the enable suppression
     * @param writableStackTrace is the writable stack trace
     */
    protected CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
