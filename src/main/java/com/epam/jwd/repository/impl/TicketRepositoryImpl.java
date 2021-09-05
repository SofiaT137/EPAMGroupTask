package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.repository.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository<Long, Ticket> {


    private static TicketRepositoryImpl instance;
    private final List<Ticket> ticketStorage = new ArrayList<>();
    private final static String UNAVAILABLE_SAVE_TICKET_EXCEPTION = "Can not save the ticket";


    public static TicketRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new TicketRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(Ticket ticket) throws UnavailableSaveTicketException {
        try{
            ticketStorage.add(ticket);
        }
        catch (Exception exception){
            throw new UnavailableSaveTicketException(UNAVAILABLE_SAVE_TICKET_EXCEPTION + "( " + exception.getMessage() + " ).");
        }
    }

    @Override
    public Ticket findById(Long id) {
        return ticketStorage.stream()
                .filter(ticket -> id.equals(ticket.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(Ticket ticket) {
        return ticketStorage.remove(ticket);
    }

    @Override
    public List<Ticket> findAllAvailable() {
        return ticketStorage.stream()
                .filter(Ticket::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAll() {
        return ticketStorage;
    }

    @Override
    public List<Ticket> findAllAvailableTicketsForKids() {
        return ticketStorage.stream()
                .filter(Ticket::isAvailableForKids)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket findByMovieName(String movieName) throws NoFindMovieException {
        return ticketStorage.stream()
                .filter(ticket -> movieName.equals(ticket.getMovieName())
                        && ticket.isAvailable())
                .findFirst().orElse(null);
    }
}
