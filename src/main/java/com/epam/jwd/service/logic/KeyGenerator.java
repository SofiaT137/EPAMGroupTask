package com.epam.jwd.service.logic;

import java.util.Random;

public class KeyGenerator {

    public static Random random = new Random();

    public static Long generateId() {
        return random.nextLong();
    }
}
