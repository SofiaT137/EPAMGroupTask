package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.User;

public interface RegistrationService {

    void signIn(String userName, String password);
    void registration(User user);
    void signOut();
    //change user info(do or not to do?)
}
