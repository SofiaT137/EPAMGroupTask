package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exeption.NoCashException;

public class UserBalanceValidation {

    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";

    public static boolean isEnoughCash(User user, double ticketCost)
            throws NoCashException {
        if(user.getBalance() - ticketCost >= 0) {
            return true;
        }

        throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
    }
}
