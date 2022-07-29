package com.example.demoProject.repository;

import com.example.demoProject.config.HibernateSession;
import com.example.demoProject.config.PersistenceManager;
import com.example.demoProject.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@ApplicationScoped
@Slf4j
public class UserRepoPersistence {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
    EntityManager entityManager = emf.createEntityManager();

//    EntityManager entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();

    public User save(User user) {
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

    public User getUserByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    public User update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
            log.info("User updated: {}", user);
            return user;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error updating user: {}", user, e);
        }
        return null;
    }

    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public Boolean delete(String username) {
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
