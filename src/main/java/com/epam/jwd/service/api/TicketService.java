package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;

import java.util.List;

public interface TicketService {

    void createTicketList(List<Ticket> listOfTickets);
    void createTicket(Ticket ticket);
    void deleteTicketsByMovieName(String movieName);
    List<Ticket> getTicketsByMovieName(String movieName);
}
