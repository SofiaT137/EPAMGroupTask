package com.epam.jwd.controller;

import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.UnavailableSaveTicketException;
import com.epam.jwd.service.impl.SellerServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

public class Controller {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();
        SellerService seller = new SellerServiceImpl();

        try {
            seller.createFranceMovieTicketList();
            seller.createRussianMovieTicketList();
            seller.createUSAMovieTicketList();
        } catch (UnavailableSaveTicketException e) {
            //log
        }
    }
}
