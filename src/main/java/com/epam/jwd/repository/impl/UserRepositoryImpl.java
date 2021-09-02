package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository<Long, User> {

    private static UserRepositoryImpl instance;
    private final List<User> userStorage = new ArrayList<>();

    private UserRepositoryImpl() {
    }

    @Override
    public void save(User user) {
        userStorage.add(user);
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }

        return instance;
    }

    @Override
    public User findById(Long id) {
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
        return userStorage.stream()
                .filter(user -> userName.equals(user.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(User user) {
        return userStorage.remove(user);
    }

    @Override
    public User findUser(User user) {
        return userStorage.get(userStorage.indexOf(user));
    }
}
