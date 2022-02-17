package com.kgozdz.centus.repository.implementation;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.model.User;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.utils.Cryptography;
import com.kgozdz.centus.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;

public class UserRepository implements IUserRepository {
    @Override
    public void login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String passwordHash = null;
        try {
            passwordHash = Cryptography.getPasswordHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Query query = session.createQuery("from User where username=:username and passwordHash=:passwordHash", User.class);
        query.setParameter("username", username);
        query.setParameter("passwordHash", passwordHash);

        var result = query.getResultList();
        session.getTransaction().commit();

        if(result != null && result.size()>0){
            User user = (User) result.get(0);
            UserSession.getInstance(user.userId, user.userName);
        }
        session.close();
    }

    @Override
    public void register(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where id=:id", User.class);
        query.setParameter("id", id);

        var result = query.getResultList();
        session.close();

        User user= null;
        if(result != null && result.size()>0){
            user = (User) result.get(0);
        }
        return user;
    }
}
