package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.UnavailableTicketException;
import com.epam.jwd.service.validation.TicketValidation;
import com.epam.jwd.service.validation.UserValidation;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private User user;
    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();
    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final String UNAVAILABLE_TICKET_EXCEPTION = "This ticket is not available";
    private static final String NO_FIND_MOVIE_EXCEPTION = "This film is not found";

    @Override
    public void registration(User user) throws UnavailableSaveUserException {
        userRepository.save(user);
        this.user = userRepository.findUser(user);
    }

    @Override
    public double checkBalance(String userName) {
        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) throws IllegalNameSizeException {
        if(!UserValidation.isValidName(userName)){
            throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
        }
        user.setName(userName);
    }

    @Override
    public void changeUserAge(int age) throws IllegalAgeException {
        if(!UserValidation.isPositiveAge(age)){
            throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
        }
        user.setAge(age);
    }

    @Override
    public void changeUserEmail(String userEmail) throws IllegalEmailException {
        if(!userEmail.matches(EMAIL_PATTERN)){
            throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
        }
        user.setEmail(userEmail);
    }

    @Override
    public void buyTicket(String movieName)
            throws UnavailableTicketException, NoCashException, NoFindMovieException {
        Ticket ticket = ticketRepository.findByMovieName(movieName);

        if (ticket == null){
            throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
        }
        if(!TicketValidation.isAvailable(ticket)){
            throw new UnavailableTicketException(UNAVAILABLE_TICKET_EXCEPTION);
        }
        if(!UserValidation.isEnoughCash(user,ticket.getPrice())){
            throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
        }
        user.addTicket(ticket);
        ticketRepository.delete(ticket);
    }

    @Override
    public double checkTicketPrice(String movieName) throws NoFindMovieException {
        Ticket ticket =  ticketRepository.findByMovieName(movieName);
        if (ticket == null){
            throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
        }
        return ticket.getPrice();
    }

    @Override
    public List<Ticket> getAvailableTickets() {
        return ticketRepository.findAllAvailable();
    }

    @Override
    public List<Ticket> getAvailableForKidsTickets() {
        return ticketRepository.findAllAvailableTicketsForKids();
    }

    @Override
    public List<Ticket> getTicketsByMovieName(String movieName) {
        return ticketRepository.findAllAvailable().stream()
                .filter(movie -> movie.getMovieName().equals(movieName))
                .collect(Collectors.toList());
    }

    @Override
    public void signIn(String userName) {
        this.user = userRepository.findByUserName(userName);
    }

    @Override
    public void signOut() {
        this.user = null;
    }
}
