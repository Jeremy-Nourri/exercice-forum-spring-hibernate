package com.example.forum.service;

import com.example.forum.model.User;

public interface IUserService {
    User saveUser(User user);
    User updateUser(User user);
    boolean deleteUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean signIn(String username, String password);
    boolean verifyIfUserIsLogged();
    void logout();
    User getLoggedUser();
}
