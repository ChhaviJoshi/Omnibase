package com.omnibase.app.service;

import com.omnibase.app.model.User;
import com.omnibase.app.exceptions.UserNotFoundException;
import com.omnibase.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;

    //CONSTRUCTOR INJECTION
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //LOGGER
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        logger.info("Creating user... INFO");
        logger.debug("Creating user... DEBUG");
        logger.warn("Creating user... WARN");
        logger.trace("Creating user... TRACE");
        logger.error("Creating user... ERROR");
        System.out.println(user.getEmail());

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + user.getId() + " does not exist!")
                );

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    public boolean deleteUser(int id) {
        if(!userRepository.existsById(id))
            throw new UserNotFoundException("User with ID " + id + " does not exist!");
        userRepository.deleteById(id);
        return true;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            throw new NullPointerException("No users found in the database");
        return users;
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist!")
                );
    }

    public List<User> searchUsers(String name, String email) {
        return userRepository.findByNameIgnoreCaseAndEmailIgnoreCase(name, email);
        //find-> search, By-> where, Name,Email and in ignoreCase ==> jpa would make a query on its own
    }

}
