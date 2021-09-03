package com.epam.jwd.service.impl.ticket_factory;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.logic.KeyGenerator;

public class RussianTicketFactory implements TicketFactory {

    @Override
    public Ticket createHorrorMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Viy", 14.99, true, false);
    }

    @Override
    public Ticket createAdventureTicket() {
        return new Ticket(KeyGenerator.generateId(), "Time of the first", 5.99, true, true);
    }

    @Override
    public Ticket createActionMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Brother 2. On the way home", 19.99, true, false);
    }

    @Override
    public Ticket createMelodramaMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Pregnancy test", 10.00, true, true);
    }

    @Override
    public Ticket createComedyMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Dad", 14.99, true, true);
    }

    @Override
    public Ticket createDetectiveMovieTicket() {
        return new Ticket(KeyGenerator.generateId(), "Ageev", 7.99, true, true);
    }
}
