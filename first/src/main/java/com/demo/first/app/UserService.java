package com.demo.first.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userDb = new HashMap<>();

    public User createUser(User user) {
         System.out.println(user.getEmail());
         userDb.putIfAbsent(user.getId(), user);
         //return ResponseEntity.status(HttpStatus.CREATED).body(user);
        return user;
    }

    public User updateUser(User user) {
        if(!userDb.containsKey(user.getId()))
            //return ResponseEntity.notFound().build();
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return null;
        userDb.put(user.getId(), user);
        //return ResponseEntity.status(HttpStatus.OK).body(user);
        return user;
    }

    public boolean deleteUser(int id) {
        if(!userDb.containsKey(id))
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return false;
        userDb.remove(id);
        //return ResponseEntity.ok("User Deleted");
        //return ResponseEntity.noContent().build();
        return true;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDb.values());
    }

    public User getUserById(int id) {
        return userDb.get(id);
    }

    public List<User> searchUsers(String name, String email) {
        return userDb.values().stream()
                .filter(u -> name.equals("user") || u.getName().equalsIgnoreCase(name))
                .filter(u -> email.equals("email") || u.getEmail().equalsIgnoreCase(email))
                .toList();
    }

}
