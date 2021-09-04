package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.exception.UnableSaveTicketException;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.api.TicketFactory;
import com.epam.jwd.service.impl.ticket_factory.FranceTicketFactory;
import com.epam.jwd.service.impl.ticket_factory.RussianTicketFactory;
import com.epam.jwd.service.impl.ticket_factory.USATicketFactory;

import java.util.ArrayList;
import java.util.List;

public class SellerServiceImpl implements SellerService {

    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();
    private static final int NUMBER_OF_SEATS = 5;

    @Override
    public void createUSAMovieTicketList() throws UnableSaveTicketException {
        TicketFactory factory = new USATicketFactory();
        createTicketList(fillCinemaHall(factory));
    }

    @Override
    public void createFranceMovieTicketList() throws UnableSaveTicketException {
        TicketFactory factory = new FranceTicketFactory();
        createTicketList(fillCinemaHall(factory));
    }

    @Override
    public void createRussianMovieTicketList() throws UnableSaveTicketException {
        TicketFactory factory = new RussianTicketFactory();
        createTicketList(fillCinemaHall(factory));
    }

    private void createTicketList(List<Ticket> listOfTickets) throws UnableSaveTicketException {
        for (Ticket ticket : listOfTickets) {
            ticketRepository.save(ticket);
        }
    }

    private void createTicket(Ticket ticket) throws UnableSaveTicketException {
        ticketRepository.save(ticket);
    }

    private void deleteTicketsByMovieName(String movieName) {
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getMovieName().equals(movieName)) {
                ticketRepository.delete(ticket);
            }
        }
    }

    private List<Ticket> fillCinemaHall(TicketFactory factory) { // заполни кинотеатр билетами, это по сути касса
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SEATS; i++) {
            ticketList.add(factory.createActionMovieTicket());
            ticketList.add(factory.createHorrorMovieTicket());
            ticketList.add(factory.createAdventureTicket());
            ticketList.add(factory.createComedyMovieTicket());
            ticketList.add(factory.createDetectiveMovieTicket());
            ticketList.add(factory.createMelodramaMovieTicket());
        }
        return ticketList;
    }
}
