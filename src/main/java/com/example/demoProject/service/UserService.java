package com.example.demoProject.service;

import com.example.demoProject.pojo.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class UserService {

    ArrayList<User> users  = new ArrayList<>(Arrays.asList(
            new User("admin", "admin", "admin@gmail.com"),
            new User("user1", "user1", "user1@gmail.com"),
            new User("user2", "user2", "user2@gmail.com"),
            new User("user3", "user3", "user3@gmail.com")
            ));


    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsename().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        try {
            users.add(user);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return user;
    }
    public List<User> getAllUsers() {
        return users;
    }
    public Boolean deleteUser(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }


}
