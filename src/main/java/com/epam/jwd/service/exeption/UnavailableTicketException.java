package com.epam.jwd.service.exeption;

public class UnavailableTicketException extends Exception {
    public UnavailableTicketException() {
    }

    public UnavailableTicketException(String message) {
        super(message);
    }

    public UnavailableTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableTicketException(Throwable cause) {
        super(cause);
    }

    public UnavailableTicketException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
