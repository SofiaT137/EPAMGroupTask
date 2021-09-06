package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.IdGenerator;

public class RussianTicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Viy", 14.99, false);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(IdGenerator.generateId(), "Time of the first", 5.99,  true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Brother 2. On the way home", 19.99, false);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Pregnancy test", 10.00, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Dad", 14.99, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(IdGenerator.generateId(), "Ageev", 7.99, true);
    }
}