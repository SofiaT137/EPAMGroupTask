package com.epam.jwd.repository.exception;

public class UnableSaveUserException extends Exception{
    public UnableSaveUserException() {
    }

    public UnableSaveUserException(String message) {
        super(message);
    }

    public UnableSaveUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableSaveUserException(Throwable cause) {
        super(cause);
    }

    public UnableSaveUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
