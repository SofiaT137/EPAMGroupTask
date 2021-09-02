package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.exeption.UnavailableTicketException;

public class TicketValidation {

    public static boolean isAvailable(Ticket ticket) throws UnavailableTicketException {
        return ticket.isAvailable();
    }
}
