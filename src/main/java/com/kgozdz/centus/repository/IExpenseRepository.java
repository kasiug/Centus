package com.kgozdz.centus.repository;

import com.kgozdz.centus.model.Expense;

import java.util.List;

public interface IExpenseRepository {
    List<Expense> getUserExpenses(byte month, short year);
    List<Expense> getUserExpenses(short year);
    List<Expense> getUserExpenses();
    void addExpense(Expense expense);

}
