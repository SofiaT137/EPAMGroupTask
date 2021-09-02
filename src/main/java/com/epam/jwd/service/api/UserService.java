package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.UnavailableTicketException;

import java.util.List;

public interface UserService {

    void registration(User user);
    void buyTicket(String movieName, int row, int seat) throws UnavailableTicketException, NoCashException;
    double checkTicketPrice(String movieName, int row, int seat);
    double checkBalance(String userName);
    void changeUserName(String userName) throws IllegalNameSizeException;
    void changeUserAge(int age) throws IllegalAgeException;
    void changeUserEmail(String userEmail) throws IllegalEmailException;
    List<Ticket> getAvailableTickets();
}
