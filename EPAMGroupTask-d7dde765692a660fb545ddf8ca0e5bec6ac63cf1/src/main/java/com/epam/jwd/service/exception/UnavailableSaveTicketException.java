package com.epam.jwd.service.exception;

public class UnavailableSaveTicketException extends Exception {
    public UnavailableSaveTicketException() {
    }

    public UnavailableSaveTicketException(String message) {
        super(message);
    }

    public UnavailableSaveTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableSaveTicketException(Throwable cause) {
        super(cause);
    }

    public UnavailableSaveTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
