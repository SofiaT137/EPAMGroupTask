package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;

public interface TicketFactory {

    Ticket createHorrorMovieTicket();
    Ticket createCartoonTicket();
    Ticket createActionMovieTicket();
    Ticket createDramaMovieTicket();
    Ticket createComedyMovieTicket();
}
