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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

import static com.kgozdz.centus.utils.Helpers.getCurrentMonth;
import static com.kgozdz.centus.utils.Helpers.getCurrentYear;

public class ExpenseController {
    private IUserRepository userRepository;
    private IExpenseRepository expenseRepository;

    @FXML
    private Label dateTime;

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


//    @FXML
//    protected void onLogOutButtonClick() {
//        boolean isLogged = this.userRepository.login(usernameTextField.getText(), passwordTextField.getText());
//        String message = isLogged ? "Logged" : "Invalid Username or password";
//
//        errorMessageLabel.setText(message);
//    }

    @FXML
    protected void onExitToHomePageLinkClick(ActionEvent event) throws IOException {
        this.moveToHomePage(event);
    }

    public void moveToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean isPositiveNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            if (d <0) return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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