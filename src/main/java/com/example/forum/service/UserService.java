package com.example.forum.service;

import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private HttpSession _httpSession;
    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        this._httpSession = httpSession;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(User user) {
        User userToDelete = userRepository.findById(user.getId()).orElse(null);
        if(userToDelete != null){
            userRepository.delete(userToDelete);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            _httpSession.setAttribute("isLogged", "true");
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyIfUserIsLogged() {
        try {
            String isLogged = (String) _httpSession.getAttribute("isLogged");
            return isLogged.equals("true");
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void logout() {
        _httpSession.removeAttribute("isLogged");
    }


}
