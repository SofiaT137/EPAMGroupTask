package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.IdGenerator;

public class USATicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Alien", "Horror", 9.99, true, true);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(IdGenerator.generateId(), "Back to the future", "Adventure", 12.99, true, true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "The Dark Knight", "Action", 16.99, true, true);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Titanic", "Melodrama", 14.00, true, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Free Guy", "Comedy", 11.99, true, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Midnight in the Switchgrass", "Detective", 4.99, true, false);
    }
}
