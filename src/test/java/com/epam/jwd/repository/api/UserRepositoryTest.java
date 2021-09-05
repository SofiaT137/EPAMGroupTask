package com.epam.jwd.repository.api;

import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static UserRepositoryImpl repository;
    private static List<User> storage;
    private static final long ID = 0L;
    private static final String NAME = "Jack";
    private User user;

    @BeforeAll
    static void init() {
        repository = UserRepositoryImpl.getInstance();
        storage = repository.findAll();
    }

    @BeforeEach
    void setUp() {
        user = new User(ID, NAME, 100, 20, "jack@mail.ru");
        repository.save(user);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void shouldFindUserByIdWhenIdExists() {
        assertSame(user, repository.findById(ID));
    }

    @Test
    void shouldReturnNullWhenIdDoesNotExist() {
        storage.clear();
        assertNull(repository.findById(ID));
    }

    @Test
    void shouldFindUserByNameWhenNameExists() {
        assertSame(user, repository.findByUserName(NAME));
    }

    @Test
    void shouldReturnNullWhenNameDoesNotExist() {
        storage.clear();
        assertNull(repository.findByUserName(NAME));
    }
}
