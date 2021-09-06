package com.epam.jwd.service.exception;

public class IllegalEmailException extends Throwable {
    public IllegalEmailException() {
    }

    public IllegalEmailException(String message) {
        super(message);
    }

    public IllegalEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEmailException(Throwable cause) {
        super(cause);
    }

    public IllegalEmailException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
