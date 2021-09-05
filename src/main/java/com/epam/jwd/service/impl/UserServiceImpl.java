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
import com.epam.jwd.service.exception.*;
import com.epam.jwd.service.validation.TicketValidation;
import com.epam.jwd.service.validation.UserValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import java.util.stream.Collectors;

import static com.epam.jwd.service.validation.UserValidation.isEmail;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String USER_REGISTRATION = "User is registrated!";
    private static final String USER_BALANCE = "User balance will be checked!";
    private static final String OTHER_USER_NAME = "New username is valid and will be changed!";
    private static final String NORMAL_AGE = "Age isn't negative!";
    private static final String OTHER_USER_EMAIL = "New useremail is valid and will be changed!";
    private static final String TICKET_BUYING = "Ticket was bought!";
    private static final String TICKET_PRICE = "Ticket will be checked by the price!";
    private static final String MOVIE_NAME_ON_TICKET = "Tickets will be sorted by movie name!";
    private static final String USER_ACTIONS_IN = "User signed in!";
    private static final String USER_ACTIONS_OUT = "User signed out!";

    private User user;
    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "User not found: ";
    private static final String NOT_FOUND_IN_REPOSITORY_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "User not found in repository.";
    private static final String NOT_FOUND_USERNAME_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "Not found user name : ";
    private static final String NOT_ACTIVE_MESSAGE = "User not active: ";

    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final String UNAVAILABLE_TICKET_EXCEPTION = "This ticket is not available";
    private static final String NO_FIND_MOVIE_EXCEPTION = "This film is not found";



    @Override
    public void registration(User user) {
        logger.log(Level.INFO, USER_REGISTRATION);

        try {
            userRepository.save(user);
        } catch (UnavailableSaveUserException e) {
           logger.log(Level.ERROR, e);
        }
        try {
            this.user = userRepository.findUser(user).orElseThrow(() ->
                    new UserNotFoundException(NOT_FOUND_IN_REPOSITORY_MESSAGE));
        } catch (UserNotFoundException e) {
            logger.log(Level.ERROR, e);
        }
        this.user.setActive(true);

    }


    public double getBalance() {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.log(Level.ERROR, e);
            }
        }

        logger.log(Level.INFO, USER_BALANCE);

        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.log(Level.ERROR, e);
            }
        }
        try {
            if(!UserValidation.isValidName(userName)){
               throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
           }
        } catch (IllegalNameSizeException e) {
            logger.log(Level.ERROR, e);
        }

        logger.log(Level.INFO, OTHER_USER_NAME);
        logger.log(Level.DEBUG, userName);

        user.setName(userName);
    }

    @Override
    public void changeUserAge(int age) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.log(Level.ERROR, e);
            }
        }

        try {
            if(!UserValidation.isValidAge(age)){
                 throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
             }
        } catch (IllegalAgeException e) {
            logger.log(Level.ERROR, e);
        }

        logger.log(Level.INFO, NORMAL_AGE);
        logger.log(Level.DEBUG, age);

        user.setAge(age);
    }

    @Override
    public void changeUserEmail(String userEmail) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.log(Level.ERROR, e);
            }
        }
        try {
            if(!isEmail(userEmail)){
                throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
            }
        } catch (IllegalEmailException e) {
            logger.log(Level.ERROR, e);
        }

        logger.log(Level.INFO, OTHER_USER_EMAIL);
        logger.log(Level.DEBUG, userEmail);

        user.setEmail(userEmail);
    }

    @Override
    public void buyTicket(String movieName) {
        logger.log(Level.DEBUG, movieName);

        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.log(Level.ERROR, e);
            }
        }

        Ticket ticket = null;
        try {
            ticket = ticketRepository.findByMovieName(movieName);
        } catch (NoFindMovieException e) {
            logger.log(Level.ERROR, e);
        }

        if (ticket == null){
            try {
                throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
            } catch (NoFindMovieException e) {
                logger.log(Level.ERROR, e);
            }
        }
        if(!TicketValidation.isAvailable(ticket)){
            try {
                throw new UnavailableTicketException(UNAVAILABLE_TICKET_EXCEPTION);
            } catch (UnavailableTicketException e) {
                logger.log(Level.ERROR, e);
            }
        }
        try {
            if(!UserValidation.isEnoughCash(user,ticket.getPrice())){
                throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
            }
        } catch (NoCashException e) {
            logger.log(Level.ERROR, e);
        }
        user.addTicket(ticket);
        ticketRepository.delete(ticket);

        logger.log(Level.INFO, TICKET_BUYING);
    }

    @Override
    public double checkTicketPrice(String movieName) {
        Ticket ticket = null;
        try {
            ticket = ticketRepository.findByMovieName(movieName);
        } catch (NoFindMovieException e) {
            logger.log(Level.ERROR, e);
        }
        if (ticket == null){
            try {
                throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
            } catch (NoFindMovieException e) {
                logger.log(Level.ERROR, e);
            }
        }

        logger.log(Level.INFO, TICKET_PRICE);
        logger.log(Level.DEBUG, movieName);

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
        logger.log(Level.INFO, MOVIE_NAME_ON_TICKET);
        logger.log(Level.DEBUG, movieName);

        return ticketRepository.findAllAvailable().stream()
                .filter(movie -> movie.getMovieName().equals(movieName))
                .collect(Collectors.toList());
    }

    @Override
    public void signIn(String userName) {
        logger.log(Level.INFO, USER_ACTIONS_IN);
        logger.log(Level.DEBUG, userName);

        try {
            this.user = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UserNotFoundException(NOT_FOUND_USERNAME_MESSAGE + userName));
        } catch (UserNotFoundException e) {
            logger.log(Level.ERROR, e);
        }
        user.setActive(true);
    }

    @Override
    public void signOut() {
        user.setActive(false);

        logger.log(Level.INFO, USER_ACTIONS_OUT);
    }
}
