package com.epam.jwd.service.exception;

public class NoCashException extends Throwable {

    public NoCashException() {
    }

    public NoCashException(String message) {
        super(message);
    }

    public NoCashException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCashException(Throwable cause) {
        super(cause);
    }

    public NoCashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
