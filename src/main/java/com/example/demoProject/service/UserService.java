package com.example.demoProject.service;

import com.example.demoProject.config.PersistenceManager;
import com.example.demoProject.pojo.User;
import com.example.demoProject.repository.UserRepoPersistence;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Slf4j
public class UserService {

    @Inject
    private UserRepoPersistence userRepo;
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.getAll();
    }
    public Boolean deleteUser(String username) {
        return userRepo.delete(username);
    }


}
