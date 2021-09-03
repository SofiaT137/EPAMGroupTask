package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.KeyGenerator;

public class USATicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Alien", 9.99, true, true);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(KeyGenerator.generateId(), "Back to the future", 12.99, true, true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "The Dark Knight", 16.99, true, true);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Titanic", 14.00, true, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Free Guy", 11.99, true, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Midnight in the Switchgrass", 4.99, true, false);
    }
}
