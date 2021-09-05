package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    @Override
    public void registration(User user) {
        userRepository.save(user);
        this.user = userRepository.findUser(user);

        logger.log(Level.INFO, USER_REGISTRATION);
    }

    @Override
    public double checkBalance(String userName) {
        logger.log(Level.INFO, USER_BALANCE);
        logger.log(Level.DEBUG, userName);

        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) {
            if(UserValidation.isValidName(userName)){
                logger.log(Level.INFO, OTHER_USER_NAME);
                logger.log(Level.DEBUG, userName);

                user.setName(userName);
            }

    }

    @Override
    public void changeUserAge(int age) {
            if(UserValidation.isPositiveAge(age)) {
                user.setAge(age);

                logger.log(Level.INFO, NORMAL_AGE);
                logger.log(Level.DEBUG, age);
            }
    }

    @Override
    public void changeUserEmail(String userEmail) {
            if(UserValidation.isEmail(userEmail)){
                user.setEmail(userEmail);

                logger.log(Level.INFO, OTHER_USER_EMAIL);
                logger.log(Level.DEBUG, userEmail);
            }

    }

    @Override
    public void buyTicket(String movieName) {
        logger.log(Level.DEBUG, movieName);

        Ticket ticket = ticketRepository.findByMovieName(movieName);


        if (TicketValidation.isAvailable(ticket)
                && UserValidation.isEnoughCash(user, ticket.getPrice())) {
            user.addTicket(ticket);
            ticketRepository.delete(ticket);

            logger.log(Level.INFO, TICKET_BUYING);

        }
    }

    @Override
    public double checkTicketPrice(String movieName) {
        logger.log(Level.INFO, TICKET_PRICE);
        logger.log(Level.DEBUG, movieName);

        return ticketRepository.findByMovieName(movieName)
                .getPrice();
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
        this.user = userRepository.findByUserName(userName);

        logger.log(Level.INFO, USER_ACTIONS_IN);
        logger.log(Level.DEBUG, userName);
    }

    @Override
    public void signOut() {
        this.user = null;

        logger.log(Level.INFO, USER_ACTIONS_OUT);
    }
}
