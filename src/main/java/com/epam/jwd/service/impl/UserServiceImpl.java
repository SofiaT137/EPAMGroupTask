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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private User user;
    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    @Override
    public void registration(User user) {
        userRepository.save(user);
        this.user = userRepository.findUser(user);
    }

    @Override
    public double checkBalance(String userName) {
        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) throws IllegalNameSizeException {
        if(UserValidation.isValidName(userName)){
            user.setName(userName);
        }
    }

    @Override
    public void changeUserAge(int age) throws IllegalAgeException {
        if(UserValidation.isPositiveAge(age)){
            user.setAge(age);
        }
    }

    @Override
    public void changeUserEmail(String userEmail) throws IllegalEmailException {
        if(UserValidation.isEmail(userEmail)){
            user.setEmail(userEmail);
        }
    }

    @Override
    public void buyTicket(String movieName)
            throws UnavailableTicketException, NoCashException {
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
    public void signIn(String userName) {
        this.user = userRepository.findByUserName(userName);
    }

    @Override
    public void signOut() {
        this.user = null;
    }
}
