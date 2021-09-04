package com.epam.jwd.repository.exception;

public class UnableSaveTicketException extends Exception {

    public UnableSaveTicketException() {
    }

    public UnableSaveTicketException(String message) {
        super(message);
    }

    public UnableSaveTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableSaveTicketException(Throwable cause) {
        super(cause);
    }

    public UnableSaveTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
