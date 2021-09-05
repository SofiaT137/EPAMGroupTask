package com.epam.jwd.service.logic;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class KeyGenerator {

    public static Random random = new Random();

    private static final Logger logger = LogManager.getLogger(KeyGenerator.class);

    private static final String USER_BALANCE = "Id will be generated!";

    public static Long generateId() {
        logger.log(Level.INFO, USER_BALANCE);

        return random.nextLong();
    }
}
