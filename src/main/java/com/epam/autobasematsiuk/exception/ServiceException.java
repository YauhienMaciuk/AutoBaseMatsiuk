package com.epam.autobasematsiuk.exception;

/**
 * The class ServiceException.
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 6587997541465367222L;

    /**
     * Instantiates a new technical exception.
     */
    public ServiceException() {
        super();
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param message is the message
     * @param cause   is the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new technical exception.
     *
     * @param cause is the cause
     */
    public ServiceException(Throwable cause) {
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
    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
