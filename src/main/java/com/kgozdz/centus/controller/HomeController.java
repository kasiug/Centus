package com.kgozdz.centus.controller;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.repository.IBudgetRepository;
import com.kgozdz.centus.repository.IExpenseRepository;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.BudgetRepository;
import com.kgozdz.centus.repository.implementation.ExpenseRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
import com.kgozdz.centus.utils.Helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class HomeController {
    private IUserRepository userRepository;
    private IExpenseRepository expenseRepository;
    private IBudgetRepository budgetRepository;

    @FXML
    private TextField expensesField;

    @FXML
    private TextField remainingAmountField;

    @FXML
    private Button logOutButton;

    @FXML
    private Button addExpenseButton;

    @FXML
    private Button changeLimitButton;

    @FXML
    private Button monthlyStatement;

    @FXML
    private Button yearlyStatement;

    public HomeController() {
        this.userRepository = new UserRepository();
        this.expenseRepository = new ExpenseRepository();
        this.budgetRepository = new BudgetRepository();
    }

    @FXML
    protected void initialize() {
        logOutButton.setText("Wyloguj " + UserSession.getUserName());
        Integer currentMonth = java.time.LocalDate.now().getMonth().getValue();
        Integer currentYear = java.time.LocalDate.now().getYear();
        var userExpenses = this.expenseRepository.getUserExpenses(currentMonth.byteValue(), currentYear.shortValue());
        var expensesSum = userExpenses.stream().mapToDouble(e -> e.amount).sum();
        var userBudget = this.budgetRepository.getUserBudget(currentMonth.byteValue(), currentYear.shortValue());
        var restToSpend = userBudget != null ? userBudget.amount - expensesSum : 0;
        expensesField.setText(currencyFormat(expensesSum));
        remainingAmountField.setText(currencyFormat(restToSpend));
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Helpers.onLogOutButtonClick(event, getClass());
    }

    public void onAddExpenseButtonButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/expense-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onChangeLimitButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/limit-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onMonthlyStatementButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/monthly-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onYearlyStatementButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/yearly-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static String currencyFormat(double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pl", "pl"));
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        return format.format(value);
    }
}
