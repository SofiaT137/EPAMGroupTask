package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.exception.UnavailableTicketException;
import com.epam.jwd.service.logic.KeyGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketValidation {

    private static final String TICKET_UNAVAILABLE_EXCEPTION_MESSAGE = "These ticket isn't available, try any other";

    private static final Logger logger = LogManager.getLogger(TicketValidation.class);

    private static final String TICKET_ACCESS = "Ticket is available!";

    public static boolean isAvailable(Ticket ticket) throws UnavailableTicketException {
        if (ticket.isAvailable()) {
            logger.log(Level.INFO, TICKET_ACCESS);

            return true;
        }

        throw new UnavailableTicketException(TICKET_UNAVAILABLE_EXCEPTION_MESSAGE);
    }
}
