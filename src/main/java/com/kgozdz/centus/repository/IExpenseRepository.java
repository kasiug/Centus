package com.kgozdz.centus.repository;

import com.kgozdz.centus.model.Expense;

import java.util.List;

public interface IExpenseRepository {
    List<Expense> getUserExpenses(byte month, short year);
    void addExpense( Expense expense);

}
