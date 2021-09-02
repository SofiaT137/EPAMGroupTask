package com.epam.jwd.repository.api;


import com.epam.jwd.repository.model.Entity;

import java.util.List;

public interface UserRepository<V, T extends Entity<V>> {

    void save(T user);
    T findById(V id);
    T findUser(T user);
    List<T> findAll();
    T findByUserName(String userName);
    boolean delete(T user);
}
