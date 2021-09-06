package com.epam.jwd.service.exception;

public class IllegalNameSizeException extends Throwable {

    public IllegalNameSizeException() {
    }

    public IllegalNameSizeException(String message) {
        super(message);
    }

    public IllegalNameSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalNameSizeException(Throwable cause) {
        super(cause);
    }

    public IllegalNameSizeException(String message, Throwable cause,
                                    boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
