package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.IdGenerator;

public class FranceTicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Les diaboliques", "Horror", 8.99, true, true);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(IdGenerator.generateId(), "Bear", "Adventure", 6.99, true, true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "La haine", "Action", 12.99, true, false);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Lolita", "Melodrama", 15.00, true, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Presidents", "Comedy", 9.99, true, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Lupin", "Detective", 6.99, true, false);
    }
}
