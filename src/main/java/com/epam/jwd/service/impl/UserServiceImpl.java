package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.*;
import com.epam.jwd.service.validation.TicketValidation;
import com.epam.jwd.service.validation.UserValidation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "User not found: ";
    private static final String NOT_FOUND_IN_REPOSITORY_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "User not found in repository.";
    private static final String NOT_FOUND_USERNAME_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "Not found user name : ";
    private static final String NOT_ACTIVE_MESSAGE = "User not active: ";

    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();
    private User user;

    @Override

    public void registration(User user) throws UserNotFoundException {
        userRepository.save(user);
        this.user = userRepository.findUser(user).orElseThrow(() ->
                new UserNotFoundException(NOT_FOUND_IN_REPOSITORY_MESSAGE));
        this.user.setActive(true);
    }


    public double getBalance() throws UserNotActiveException {
        if (!user.isActive()) {
            throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
        }
        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) throws IllegalNameSizeException, UserNotActiveException {
        if (!user.isActive()) {
            throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
        }
        if (UserValidation.isValidName(userName)) {
            user.setName(userName);
        }
    }

    @Override
    public void changeUserAge(int age) throws IllegalAgeException, UserNotActiveException {
        if (!user.isActive()) {
            throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
        }
        if (UserValidation.isValidAge(age)) {
            user.setAge(age);
        }
    }

    @Override
    public void changeUserEmail(String userEmail) throws IllegalEmailException, UserNotActiveException {
        if (!user.isActive()) {
            throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
        }
        if (UserValidation.isEmail(userEmail)) {
            user.setEmail(userEmail);
        }
    }

    @Override
    public void buyTicket(String movieName)
            throws UnavailableTicketException, NoCashException, UserNotActiveException {
        if (!user.isActive()) {
            throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
        }
        Ticket ticket = ticketRepository.findByMovieName(movieName);

        if (TicketValidation.isAvailable(ticket)
                && UserValidation.isEnoughCash(user, ticket.getPrice())) {
            user.addTicket(ticket);
            ticketRepository.delete(ticket);
        }
    }

    @Override
    public double checkTicketPrice(String movieName) {
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
        return ticketRepository.findAllAvailable().stream()
                .filter(movie -> movie.getMovieName().equals(movieName))
                .collect(Collectors.toList());
    }

    @Override
    public void signIn(String userName) throws UserNotFoundException {
        this.user = userRepository.findByUserName(userName).orElseThrow(() ->
                new UserNotFoundException(NOT_FOUND_USERNAME_MESSAGE + userName));
        user.setActive(true);
    }

    @Override
    public void signOut() {
        user.setActive(false);
    }
}
