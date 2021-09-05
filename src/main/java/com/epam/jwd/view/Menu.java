package com.epam.jwd.view;

import com.epam.jwd.controller.Actions;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;

import java.util.List;

public class Menu {
    private static final String DELIMITER = "---------------------";

    private static final String MENU_FOR_GUEST = """ 
            Select:
            1.Sign In
            2.Registration
            """;
    private static final String MENU_FOR_USER = """
            Select:
            1.Buy a ticket
            2.Your tickets
            2.Check balance
            3.Profile settings
            4.Sign Out
            """;

    private static final String MENU_PROFILE_SETTINGS = """ 
            Select:
            1.Change name
            2.Change age
            3.Change email
            4.Back
            """;

    public static void getProfileSettingsMenu() {
        System.out.println(MENU_PROFILE_SETTINGS);
    }

    public static void getGuestMenu() {
        System.out.println(MENU_FOR_GUEST);
    }

    public static void getUserMenu(String userName) {
        getMessageWelcome(userName);
        System.out.println(MENU_FOR_USER);
    }

    public static void getMenuMovies(Ticket ticket) {
        System.out.println(DELIMITER);
        getMessageMovieName(ticket.getMovieName());
        getMessageMovieGenre(ticket.getMovieGenre());
        getMessageMoviePrice(ticket.getPrice());

        if (ticket.isAvailable()) {
            getMessageAvailableTickets();
        } else {
            getMessageNotAvailableTickets();
        }

        if (ticket.isAvailableForKids()) {
            getMessageAvailableForKids();
        } else {
            getMessageNotAvailableForKids();
        }
        System.out.println(DELIMITER);
    }

    public static void getMessageMovieName(String movieName) {
        System.out.println("Movie name: ");
    }

    public static void getMessageMovieGenre(String genre) {
        System.out.println("Genre: ");
    }

    public static void getMessageMoviePrice(double price) {
        System.out.println("Price: ");
    }

    public static void getMessageAvailableForKids() {
        System.out.println("Allowed for children");
    }

    public static void getMessageNotAvailableForKids() {
        System.out.println("Not allowed for children");
    }

    public static void getMessageAvailableTickets() {
        System.out.println("Tickets available");
    }

    public static void getMessageNotAvailableTickets() {
        System.out.println("Tickets not available");
    }

    public static void getMessageInputUserName() {
        System.out.println("Enter your name: ");
    }

    public static void getMessageInputBalance() {
        System.out.println("Enter your balance: ");
    }

    public static void getMessageInputAge() {
        System.out.println("Enter your age: ");
    }

    public static void getMessageInputEmail() {
        System.out.println("Enter your email: ");
    }

    public static void getMessageRegisteredSuccess(String userName) {
        System.out.println(userName + ", you have successfully registered.");
    }

    public static void getMessageWelcome(String userName) {
        System.out.println("Hello, " + userName);
    }

    public static void getMessageChangeUserName() {
        System.out.println("Enter new name: ");
    }

    public static void getMessageChangeUserNameSuccess() {
        System.out.println("You have successfully changed your name");
    }

    public static void getMessageChangeAge() {
        System.out.println("Enter new age: ");
    }

    public static void getMessageChangeAgeSuccess() {
        System.out.println("You have successfully changed your age");
    }

    public static void getMessageChangeEmail() {
        System.out.println("Enter new email: ");
    }

    public static void getMessageChangeEmailSuccess() {
        System.out.println("You have successfully changed your email");
    }

    public static void getMessageUserName(String userName) {
        System.out.println("User name: " + userName);
    }

    public static void getMessageAge(int age) {
        System.out.println("Age: " + age);
    }

    public static void getMessageEmail(String email) {
        System.out.println("Email: " + email);
    }

    public static void getMessageBalance(double balance) {
        System.out.println("Balance: " + balance);
    }

    public static void getInfoUser(User user) {
        getMessageUserName(user.getName());
        getMessageBalance(user.getBalance());
        getMessageAge(user.getAge());
        getMessageEmail(user.getEmail());
    }

    public static void getMessageGoodbye(String userName) {
        System.out.println("Bye, " + userName);
    }

    public static void getMessageSelectTicket() {
        System.out.println("Select movie: ");
    }

    public static void getMessageNotTickets() {
        System.out.println("No tickets purchased.");
    }

    public static void getUserTickets(List<Ticket> tickets) {
        if(tickets.isEmpty()) {
            getMessageNotTickets();
        } else {
            tickets.forEach(Menu::getMenuMovies);
        }
    }
}
