package com.epam.jwd.service.exception;

import com.epam.jwd.controller.Controller;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
