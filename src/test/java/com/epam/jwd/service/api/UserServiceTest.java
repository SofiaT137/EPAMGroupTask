package com.epam.jwd.service.api;

import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.UnavailableTicketException;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserServiceImpl service;
    private static List<User> userStorage;
    private static List<Ticket> ticketStorage;
    private User validUser;

    @BeforeAll
    static void init() {
        userStorage = UserRepositoryImpl.getInstance().findAll();
        ticketStorage = TicketRepositoryImpl.getInstance().findAll();
    }

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl();
        validUser = new User(0L, "Jack", 100, 20, "jack@mail.ru");
        service.registration(validUser);
    }

    @Test
    void shouldRegisterUserWhenTheUserIsValid() {
        User currentUser = userStorage.remove(0);
        assertSame(validUser, currentUser);
    }

    @Test
    void shouldBuyTicketWhenTheFilmExistsAndEnoughCash()
            throws UnavailableTicketException, NoCashException {
        String filmName = "Titanic";
        double price = 14.00;
        double expectedCash = validUser.getBalance() - price;
        Ticket ticket =
                new Ticket(0L, filmName, "Melodrama", price, true, true);
        ticketStorage.add(ticket);
        User currentUser = userStorage.get(0);
        service.buyTicket(filmName);
        assertEquals(0, ticketStorage.size());
        assertSame(ticket, validUser.getTickets().remove(0));
        assertEquals(0, Double.compare(expectedCash, validUser.getBalance()),
                "Money should decrease, when buying");
    }

    @Test
    void shouldThrowUnavailableTicketExceptionWhenTheFilmDoesNotExist() {
        String filmName = "Titanic";
        assertThrows(UnavailableTicketException.class,
                () -> service.buyTicket(filmName));
    }

    @Test
    void shouldThrowNoCashExceptionWhenTheUserDoesNotHaveEnoughCash() {
        User poorUser = new User(0L, "Jack", 0, 20, "jack@mail.ru");
        service.registration(poorUser);
        String filmName = "Titanic";
        Ticket ticket =
                new Ticket(0L, filmName, "Melodrama", 14.00, true, true);
        ticketStorage.add(ticket);
        assertThrows(NoCashException.class,
                () -> service.buyTicket(filmName));
    }
}
