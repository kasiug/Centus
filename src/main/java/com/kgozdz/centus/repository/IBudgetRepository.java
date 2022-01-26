package com.kgozdz.centus.repository;

import com.kgozdz.centus.model.Budget;
import com.kgozdz.centus.model.Expense;

import java.util.List;

public interface IBudgetRepository {
    Budget getUserBudget(byte month, short year);
    void setBudget(Budget budget);
}
