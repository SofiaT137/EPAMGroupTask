package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.exeption.UnavailableTicketException;

public class TicketValidation {

    private static final String TICKET_UNAVAILABLE_EXCEPTION_MESSAGE = "These ticket isn't available, try any other";

    public static boolean isAvailable(Ticket ticket) throws UnavailableTicketException {
        if(ticket.isAvailable()) {
            return true;
        }

        throw new UnavailableTicketException(TICKET_UNAVAILABLE_EXCEPTION_MESSAGE);
    }
}
