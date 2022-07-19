package com.example.demoProject.service;

import com.example.demoProject.config.PersistenceManager;
import com.example.demoProject.pojo.User;
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

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

    EntityManager entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();

    ArrayList<User> users  = new ArrayList<>(Arrays.asList(
            new User("admin", "admin", "admin@gmail.com"),
            new User("user1", "user1", "user1@gmail.com"),
            new User("user2", "user2", "user2@gmail.com"),
            new User("user3", "user3", "user3@gmail.com")
            ));


    public User getUserByUsername(String username) {
        return entityManager.find(User.class, username);
    }
    /* public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsename().equals(username))
                .findFirst()
                .orElse(null);
    }*/



    @Transactional
    public User addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            log.info("User added: {}", user);
            return user;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error adding user: {}", user, e);
        }
        return null;
    }
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    public Boolean deleteUser(String username) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, username);
        if (user != null) {
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            return true;
        }
        entityManager.getTransaction().commit();
        return false;
    }


}
