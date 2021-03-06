package com.kgozdz.centus.controller;

import com.kgozdz.centus.model.Budget;
import com.kgozdz.centus.model.Expense;
import com.kgozdz.centus.repository.IBudgetRepository;
import com.kgozdz.centus.repository.IExpenseRepository;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.BudgetRepository;
import com.kgozdz.centus.repository.implementation.ExpenseRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
import com.kgozdz.centus.utils.Helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyController {
    private IUserRepository userRepository;
    private IExpenseRepository expenseRepository;
    private IBudgetRepository budgetRepository;

    @FXML
    private BarChart barChart;

    @FXML
    private Button logOutButton;

    @FXML
    private Hyperlink exitToHomePageLink;

    public MonthlyController() {
        this.userRepository = new UserRepository();
        this.expenseRepository = new ExpenseRepository();
        this.budgetRepository = new BudgetRepository();
    }

    @FXML
    protected void initialize() {
        Integer currentYear = java.time.LocalDate.now().getYear();
        var userExpenses = this.expenseRepository.getUserExpenses(currentYear.shortValue());
        var userBudgets = this.budgetRepository.getUserBudgets(currentYear.shortValue());

        Map<Byte, Double> monthlyExpenses = userExpenses.stream()
                .collect(Collectors
                        .groupingBy(Expense::getMonth, Collectors.summingDouble(e -> e.getAmount())));

        Map<Byte, Double> monthlyBudgets = userBudgets.stream()
                .collect(Collectors
                        .groupingBy(Budget::getMonth, Collectors.summingDouble(b -> b.getAmount())));

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("wydatki");
        monthlyExpenses.forEach((month, expensesSum) ->
                series1.getData().add(new XYChart.Data(month.toString(), expensesSum))
        );

        series2.setName("limit");
        monthlyBudgets.forEach((month, expensesSum) ->
                series2.getData().add(new XYChart.Data(month.toString(), expensesSum))
        );

        barChart.getData().addAll(series1, series2);
    }

    @FXML
    protected void onExitToHomePageLinkClick(ActionEvent event) throws IOException {
        this.moveToHomePage(event);
    }

    public void moveToHomePage(ActionEvent event) throws IOException {
        Helpers.moveToHomePage(event, getClass());
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Helpers.onLogOutButtonClick(event, getClass());
    }
}
