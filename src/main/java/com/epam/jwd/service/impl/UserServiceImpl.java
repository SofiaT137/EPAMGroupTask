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
    public void buyTicket(String movieName, int row, int seat)
            throws UnavailableTicketException, NoCashException {
        Ticket ticket = ticketRepository.findByPosition(movieName, row, seat);

        if (TicketValidation.isAvailable(ticket)
                && UserValidation.isEnoughCash(user, ticket.getPrice())) {
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
