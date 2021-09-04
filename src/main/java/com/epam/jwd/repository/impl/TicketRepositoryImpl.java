package com.epam.jwd.repository.impl;

import com.epam.jwd.controller.Controller;
import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.model.Ticket;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository<Long, Ticket> {

    private static final Logger logger = LogManager.getLogger(TicketRepositoryImpl.class);

    private static final String CHECK_FOR_NULL = "Instance is null";

    private static final String ID_SORTING = "ID is searched";

    private static final String REMOVED_TICKET = "Ticket was remove!";

    private static final String ALL_AVAILABLE_TICKETS = "All available tickets are searched!";

    private static final String ALL_AVAILABLE_TICKETS_FOR_KIDS = "All available tickets for kids are searched!";

    private static final String MOVIE_NAME = "Movie was found by movie name";

    private static final String ALL_TICKETS = "There is search all tickets here";



    private static TicketRepositoryImpl instance;
    private final List<Ticket> ticketStorage = new ArrayList<>();


    public static TicketRepositoryImpl getInstance() {
        if (instance == null) {
            logger.log(Level.INFO, CHECK_FOR_NULL);
            instance = new TicketRepositoryImpl();
        }

        return instance;
    }

    @Override
    public void save(Ticket ticket) {
        ticketStorage.add(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        logger.log(Level.DEBUG, ID_SORTING);
        return ticketStorage.stream()
                .filter(ticket -> id.equals(ticket.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(Ticket ticket) {
        logger.log(Level.DEBUG, REMOVED_TICKET);

        return ticketStorage.remove(ticket);
    }

    @Override
    public List<Ticket> findAllAvailable() {
        logger.log(Level.DEBUG, ALL_AVAILABLE_TICKETS);
        return ticketStorage.stream()
                .filter(Ticket::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAll() {
        logger.log(Level.DEBUG, ALL_TICKETS);
        return ticketStorage;
    }

    @Override
    public List<Ticket> findAllAvailableTicketsForKids() {
        logger.log(Level.DEBUG, ALL_AVAILABLE_TICKETS_FOR_KIDS);
        return ticketStorage.stream()
                .filter(Ticket::isAvailableForKids)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket findByMovieName(String movieName) {
        logger.log(Level.DEBUG, MOVIE_NAME);
        return ticketStorage.stream()
                .filter(ticket -> movieName.equals(ticket.getMovieName())
                        && ticket.isAvailable())
                .findFirst().orElse(null);
    }
}
