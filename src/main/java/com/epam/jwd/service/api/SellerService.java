package com.epam.jwd.service.api;

import com.epam.jwd.repository.exception.UnableSaveTicketException;

public interface SellerService {

    void createUSAMovieTicketList() throws UnableSaveTicketException;
    void createFranceMovieTicketList() throws UnableSaveTicketException;
    void createRussianMovieTicketList() throws UnableSaveTicketException;}
