package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exeption.NoCashException;
import com.epam.jwd.service.exeption.UnavailableTicketException;
import com.epam.jwd.service.validation.TicketValidation;
import com.epam.jwd.service.validation.UserBalanceValidation;

public class UserServiceImpl implements UserService {

    private User user;
    private UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

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
    public void changeUserName(String userName) {
        user.setName(userName);
    }

    @Override
    public void changeUserAge(int age) {
        user.setAge(age);
    }

    @Override
    public void changeUserEmail(String userEmail) {
        user.setEmail(userEmail);
    }

    @Override
    public void buyTicket(String movieName, int row, int seat)
            throws UnavailableTicketException, NoCashException {
        Ticket ticket = ticketRepository.findByPosition(movieName, row, seat);

        if (TicketValidation.isAvailable(ticket) && UserBalanceValidation.isEnoughCash(user, ticket.getPrice())) {
            user.addTicket(ticket);
            ticketRepository.delete(ticket);

        }
    }

    @Override
    public double checkTicketPrice(String movieName, int row, int seat) {
        return ticketRepository.findByPosition(movieName, row, seat)
                .getPrice();
    }
}
