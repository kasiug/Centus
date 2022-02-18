package com.kgozdz.centus.controller;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.model.Expense;
import com.kgozdz.centus.repository.IExpenseRepository;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.ExpenseRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
import com.kgozdz.centus.utils.Helpers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.io.IOException;
import static com.kgozdz.centus.utils.Helpers.*;
import static com.kgozdz.centus.utils.Helpers.getCurrentMonth;
import static com.kgozdz.centus.utils.Helpers.getCurrentYear;

public class ExpenseController {
    private IUserRepository userRepository;
    private IExpenseRepository expenseRepository;

    @FXML
    private TextField expenseAmount;

    @FXML
    private Button logOutButton;

    @FXML
    private Hyperlink exitToHomePageLink;

    public ExpenseController() {
        this.userRepository = new UserRepository();
        this.expenseRepository = new ExpenseRepository();
    }

    @FXML
    protected void initialize() {
        expenseAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if(!isPositiveNumeric(newValue)){
                    expenseAmount.setText("");
                }
            }
        });
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

    public void onSaveButtonClick(ActionEvent event) throws IOException {
        var expenseAmount = Float.parseFloat(this.expenseAmount.getText());
        var user = this.userRepository.getUser(UserSession.getUserId());
        var expense = new Expense();
        expense.setAmount(expenseAmount);
        expense.setMonth((byte)getCurrentMonth());
        expense.setYear((short)getCurrentYear());
        expense.setUser(user);
        this.expenseRepository.addExpense(expense);
        this.moveToHomePage(event);
    }
}