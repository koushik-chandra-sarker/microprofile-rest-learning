package com.example.demoProject.service;

import com.example.demoProject.pojo.User;
import com.example.demoProject.repository.UserRepoHibernate;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class UserServiceHb {


    @Inject
    private UserRepoHibernate userRepo;

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepo.callInsertSp(user);
    }
    public List<User> getAllUsers() {
        return userRepo.getAll();
    }
    public Boolean deleteUser(String username) {
        return userRepo.delete(username);
    }


}
