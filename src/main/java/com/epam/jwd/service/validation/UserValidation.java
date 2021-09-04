package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.logic.KeyGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidation {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";

    private static final Logger logger = LogManager.getLogger(UserValidation.class);

    private static final String CASH = "The cash is enough!";

    private static final String VALID_NAME = "The name is valid!";

    private static final String POSITIVE_AGE = "The age is positive!";

    private static final String EMAIL = "The email is valid!";

    public static boolean isEnoughCash(User user, double ticketCost)
            throws NoCashException {
        if(user.getBalance() - ticketCost >= 0) {
            logger.log(Level.DEBUG, CASH);

            return true;
        }

        throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
    }

    public static boolean isValidName(String name) throws IllegalNameSizeException {
        if(name.length() > 0) {
            logger.log(Level.DEBUG, VALID_NAME);

            return true;
        }

        throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
    }

    public static boolean isPositiveAge(int age) throws IllegalAgeException {
        if(age > 0) {
            logger.log(Level.DEBUG, POSITIVE_AGE);

            return true;
        }

        throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
    }

    public static boolean isEmail(String email) throws IllegalEmailException {
        if(email.matches(EMAIL_PATTERN)) {
            logger.log(Level.DEBUG, EMAIL);

            return true;
        }

        throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
    }
}
