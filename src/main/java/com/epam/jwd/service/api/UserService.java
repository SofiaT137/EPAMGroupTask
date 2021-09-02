package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exeption.UnavailableTicketException;

public interface UserService {

    void registration(User user);
    void buyTicket(String movieName, int row, int seat) throws UnavailableTicketException;
    double checkTicketPrice(String movieName, int row, int seat);
    double checkBalance(String userName);
    void changeUserName(String userName);
    void changeUserAge(int age);
    void changeUserEmail(String userEmail);
}
