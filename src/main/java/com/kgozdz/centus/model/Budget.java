package com.kgozdz.centus.model;

import javax.persistence.*;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer budgetId;

    @Column(name = "amount", nullable = false)
    public Float amount;

    @Column(name = "month", nullable = false, length = 2)
    public byte month;

    @Column(name = "year", nullable = false, length = 4)
    public short year;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

