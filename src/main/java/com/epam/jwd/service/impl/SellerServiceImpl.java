package com.epam.jwd.service.impl;

import com.epam.jwd.controller.Controller;
import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.impl.ticket_factory.FranceTicketFactory;
import com.epam.jwd.service.impl.ticket_factory.RussianTicketFactory;
import com.epam.jwd.service.impl.ticket_factory.USATicketFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SellerServiceImpl implements SellerService {

    private static final Logger logger = LogManager.getLogger(SellerServiceImpl.class);

    private static final String USA_MOVIE_TICKETS = "List of USA movie tickets was created!";

    private static final String FRANCE_MOVIE_TICKETS = "List of France movie tickets was created!";

    private static final String RUSSIAN_MOVIE_TICKETS = "List of France movie tickets was created!";

    private static final String MOVIE_TICKETS = "List of movie tickets was created!";

    private static final String SAVE_TICKET = "Ticket was saved!";

    private static final String MOVIE_NAME_TICKET = "Ticket was deleted by movie name!";

    private static final String FULL_CINEMA_HALL = "Cinema hall was full";


    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();
    private static final int NUMBER_OF_SEATS = 5;

    @Override
    public void createUSAMovieTicketList() {
        TicketFactory factory = new USATicketFactory();
        createTicketList(fillCinemaHall(factory));

        logger.log(Level.INFO, USA_MOVIE_TICKETS);
    }

    @Override
    public void createFranceMovieTicketList() {
        TicketFactory factory = new FranceTicketFactory();
        createTicketList(fillCinemaHall(factory));

        logger.log(Level.INFO, FRANCE_MOVIE_TICKETS);
    }

    @Override
    public void createRussianMovieTicketList() {
        TicketFactory factory = new RussianTicketFactory();
        createTicketList(fillCinemaHall(factory));

        logger.log(Level.INFO, RUSSIAN_MOVIE_TICKETS);
    }

    private void createTicketList(List<Ticket> listOfTickets) {
        for (Ticket ticket : listOfTickets) {
            ticketRepository.save(ticket);
        }

        logger.log(Level.INFO, MOVIE_TICKETS);
    }

    private void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);

        logger.log(Level.DEBUG, SAVE_TICKET);
    }

    private void deleteTicketsByMovieName(String movieName) {
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getMovieName().equals(movieName)) {
                ticketRepository.delete(ticket);
            }

            logger.log(Level.INFO, MOVIE_NAME_TICKET);
        }
    }

    private List<Ticket> fillCinemaHall(TicketFactory factory) {
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SEATS; i++) {
            ticketList.add(factory.createActionMovieTicket());
            ticketList.add(factory.createHorrorMovieTicket());
            ticketList.add(factory.createAdventureTicket());
            ticketList.add(factory.createComedyMovieTicket());
            ticketList.add(factory.createDetectiveMovieTicket());
            ticketList.add(factory.createMelodramaMovieTicket());
        }

        logger.log(Level.DEBUG, FULL_CINEMA_HALL);

        return ticketList;
    }
}
