package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;

public class UserValidation {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final int MAX_NAME_LENGTH = 30;
    private static final int MAX_AGE = 125;

    public static boolean isEnoughCash(User user, double ticketCost)
            throws NoCashException {
        if(user.getBalance() - ticketCost < 0) {
            throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);

        }
        return true;
    }

    public static boolean isValidName(String name) throws IllegalNameSizeException {
        if(name.length() == 0) {
            throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
        }
        return true;
    }

    public static boolean isValidAge(int age) throws IllegalAgeException {
        if(age <= 0) {
            throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
        }
        return true;
    }

    public static boolean isEmail(String email) throws IllegalEmailException {
        if(!email.matches(EMAIL_PATTERN)) {
            throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
        }
        return true;
    }
}
