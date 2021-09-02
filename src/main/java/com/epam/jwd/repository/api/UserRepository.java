package com.epam.jwd.repository.api;


import com.epam.jwd.repository.model.Entity;
import com.epam.jwd.repository.model.User;

import java.util.List;

public interface UserRepository<V, T extends Entity<V>> {

    void save(User user);
    T findById(T id);
    List<T> findAll();
    T findByUserName(String userName);
    T delete(User user);
}
