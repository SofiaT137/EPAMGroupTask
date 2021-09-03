package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.KeyGenerator;

public class FranceTicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Les diaboliques", 8.99, true, true);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(KeyGenerator.generateId(), "Bear", 6.99, true, true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "La haine", 12.99, true, false);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Lolita", 15.00, true, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "presidents", 9.99, true, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Lupin", 6.99, true, false);
    }
}
