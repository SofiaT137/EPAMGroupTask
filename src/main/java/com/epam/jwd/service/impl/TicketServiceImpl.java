package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    @Override
    public void createTicketList(List<Ticket> listOfTickets) {
        for (Ticket ticket : listOfTickets) {
            ticketRepository.save(ticket);
        }
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketsByMovieName(String movieName) {
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getMovieName().equals(movieName)) {
                ticketRepository.delete(ticket);
            }
        }
    }

}
