package com.example.demoProject.repository;

import com.example.demoProject.config.HibernateSession;
import com.example.demoProject.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class UserRepoHibernate {
    SessionFactory sessionFactory= HibernateSession.getSessionFactory();
    public User save(User user){
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String username = (String) session.save(user);
            session.getTransaction().commit();
            return session.get(User.class, username);
        }catch (Exception e){
            if (session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
    public User getUserByUsername(String username){
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User update(User user){
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user1 = (User) session.merge(user);
            session.getTransaction().commit();
            return user1;
        }catch (Exception e){
            if (session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
    public List<User> getAll(){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean delete(String username){
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, username);
            if (user != null) {
                session.remove(user);
                session.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if (session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }


}
