package com.epam.autobasematsiuk.exception;

/**
 * The class DAOException.
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = -9103389735361927820L;

    /**
     * Instantiates a new technical exception.
     */
    public DAOException() {
        super();
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     * @param cause   is the cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param cause is the cause
     */
    public DAOException(Throwable cause) {
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
    protected DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
