package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.*;

import java.util.List;

public interface UserService {

    void registration(User user) throws UserNotFoundException;
    void buyTicket(String movieName) throws UnavailableTicketException, NoCashException, UserNotActiveException;
    double checkTicketPrice(String movieName);
    double getBalance() throws UserNotActiveException;
    void changeUserName(String userName) throws IllegalNameSizeException, UserNotActiveException;
    void changeUserAge(int age) throws IllegalAgeException, UserNotActiveException;
    void changeUserEmail(String userEmail) throws IllegalEmailException, UserNotActiveException;
    List<Ticket> getAvailableTickets();
    List<Ticket> getAvailableForKidsTickets();
    List<Ticket> getTicketsByMovieName(String movieName);
    void signIn(String userName) throws UserNotFoundException;
    void signOut();
}
