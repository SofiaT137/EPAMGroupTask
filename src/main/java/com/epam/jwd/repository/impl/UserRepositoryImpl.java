package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository<Long, User> {

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    private static final String CHECK_FOR_NULL = "Instance is null";

    private static final String SAVED_USER = "Ticket was saved!";

    private static final String ID_SORTING = "ID is searching";

    private static final String REMOVED_USER = "User was remove!";

    private static final String USERNAME_CHECK = "Username is searching!";

    private static final String USER_SEARCH = "User is being searched";

    private static UserRepositoryImpl instance;
    private final List<User> userStorage = new ArrayList<>();

    private UserRepositoryImpl() {
    }

    @Override
    public void save(User user) {
        userStorage.add(user);

        logger.log(Level.INFO, SAVED_USER);
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            logger.log(Level.INFO, CHECK_FOR_NULL);

            instance = new UserRepositoryImpl();
        }

        return instance;
    }

    @Override
    public User findById(Long id) {
        logger.log(Level.DEBUG, ID_SORTING);

        return userStorage.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userStorage;
    }

    @Override
    public User findByUserName(String userName) {
        logger.log(Level.DEBUG, USERNAME_CHECK);

        return userStorage.stream()
                .filter(user -> userName.equals(user.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(User user) {
        logger.log(Level.DEBUG, REMOVED_USER);

        return userStorage.remove(user);
    }

    @Override
    public User findUser(User user) {
        logger.log(Level.DEBUG, USER_SEARCH);

        return userStorage.get(userStorage.indexOf(user));
    }
}
