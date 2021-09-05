package com.epam.jwd.controller;

import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.impl.SellerServiceImpl;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {

        SellerService service = new SellerServiceImpl();
        try {
            service.createUSAMovieTicketList();
            service.createRussianMovieTicketList();
            service.createFranceMovieTicketList();
        } catch (UnavailableSaveTicketException exception) {
            //TODO log
        }
    }
}
