package com.kgozdz.centus.repository.implementation;

import com.kgozdz.centus.model.User;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.utils.Cryptography;
import com.kgozdz.centus.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;

public class UserRepository implements IUserRepository {
    @Override
    public boolean login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String passwordHash = null;
        try {
            passwordHash = Cryptography.getPasswordHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("pass hash is " + passwordHash);

        Query query = session.createQuery("from User where username=:username and passwordHash=:passwordHash", User.class);
        query.setParameter("username", username);
        query.setParameter("passwordHash", passwordHash);

        var result = query.getResultList();
        session.getTransaction().commit();

        return result.size() == 1;
    }

    @Override
    public void register(User user) {

    }
}
