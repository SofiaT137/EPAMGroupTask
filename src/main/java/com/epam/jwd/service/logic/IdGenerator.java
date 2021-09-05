package com.epam.jwd.service.logic;


public class IdGenerator {

    private static Long id = 0L;


    public static Long generateId() {
        return id++;
    }
}
