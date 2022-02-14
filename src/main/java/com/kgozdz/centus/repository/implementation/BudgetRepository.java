package com.kgozdz.centus.repository.implementation;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.model.Budget;
import com.kgozdz.centus.model.Expense;
import com.kgozdz.centus.repository.IBudgetRepository;
import com.kgozdz.centus.repository.IExpenseRepository;
import com.kgozdz.centus.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;


public class BudgetRepository implements IBudgetRepository {

    @Override
    public Budget getUserBudget(byte month, short year) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int userId = UserSession.getUserId();
        Query query = session.createQuery("from Budget where userId=:userId and month=:month and year=:year", Budget.class);
        query.setParameter("userId", userId);

        query.setParameter("month", month);
        query.setParameter("year", year);

        var result = query.getResultList();
        if (result != null && result.size() > 0) {
            return (Budget) result.get(0);
        } else {
            return null;
        }

    }

    @Override
    public void setBudget(Budget budget) {
        var existingBudget = getUserBudget(budget.month, budget.year);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (existingBudget == null) {
            session.save(budget);
        } else {
            existingBudget.setAmount(budget.amount);
            session.update(existingBudget);
        }
        session.getTransaction().commit();


    }
}
