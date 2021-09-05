package com.epam.jwd.controller;

import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.impl.SellerServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String APP_START = "App is running!";

    public static void main(String[] args) {
        logger.log(Level.INFO, APP_START);

        SellerService service = new SellerServiceImpl();
        try {
            service.createUSAMovieTicketList();
            service.createRussianMovieTicketList();
            service.createFranceMovieTicketList();
        } catch (UnavailableSaveTicketException exception) {
            logger.log(Level.ERROR, exception);
        }
    }
}
