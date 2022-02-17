package com.kgozdz.centus.repository.implementation;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.model.Expense;
import com.kgozdz.centus.repository.IExpenseRepository;
import com.kgozdz.centus.utils.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class ExpenseRepository implements IExpenseRepository {

    @Override
    public List<Expense> getUserExpenses(byte month, short year) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int userId = UserSession.getUserId();
        Query query = session.createQuery("from Expense where userId=:userId and month=:month and year=:year", Expense.class);
        query.setParameter("userId", userId);
        query.setParameter("month", month);
        query.setParameter("year", year);

        var  result = query.getResultList();
        session.close();
        if(result!=null){
            return (List<Expense>) result;
        }else{
            return null;
        }
    }

    @Override
    public List<Expense> getUserExpenses(short year) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int userId = UserSession.getUserId();
        Query query = session.createQuery("from Expense where userId=:userId and year=:year", Expense.class);
        query.setParameter("userId", userId);
        query.setParameter("year", year);

        var  result = query.getResultList();
        session.close();
        if(result!=null){
            return (List<Expense>) result;
        }else{
            return null;
        }
    }

    @Override
    public List<Expense> getUserExpenses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int userId = UserSession.getUserId();
        Query query = session.createQuery("from Expense where userId=:userId", Expense.class);
        query.setParameter("userId", userId);

        var  result = query.getResultList();
        session.close();
        if(result!=null){
            return (List<Expense>) result;
        }else{
            return null;
        }
    }

    @Override
    public void addExpense(Expense expense) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(expense);
        session.getTransaction().commit();
        session.close();
    }
}
