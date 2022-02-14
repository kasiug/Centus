package com.kgozdz.centus.controller;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.model.Budget;
import com.kgozdz.centus.repository.IBudgetRepository;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.BudgetRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
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

import java.io.IOException;

import static com.kgozdz.centus.utils.Helpers.getCurrentMonth;
import static com.kgozdz.centus.utils.Helpers.getCurrentYear;

public class LimitController {

    private IUserRepository userRepository;
    private IBudgetRepository budgetRepository;

    @FXML
    private Label dateTime;

    @FXML
    private TextField amount;

    @FXML
    private Button logOutButton;

    @FXML
    private Hyperlink exitToHomePageLink;

    public LimitController() {

        this.userRepository = new UserRepository();
        this.budgetRepository = new BudgetRepository();
    }

    @FXML
    protected void initialize() {

        Integer currentMonth = java.time.LocalDate.now().getMonth().getValue();
        Integer currentYear = java.time.LocalDate.now().getYear();
        var userBudget = this.budgetRepository.getUserBudget(currentMonth.byteValue(), currentYear.shortValue());

        var budgetAmount = userBudget != null? userBudget.amount : 0.0;
        amount.setText(Double.toString(budgetAmount));

        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if(!isPositiveNumeric(newValue)){
                    amount.setText(Double.toString(budgetAmount));
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
        var budgetAmount = Float.parseFloat(this.amount.getText());
        var user = this.userRepository.getUser(UserSession.getUserId());
        var budget = new Budget();
        budget.setAmount(budgetAmount);
        budget.setMonth((byte)getCurrentMonth());
        budget.setYear((short)getCurrentYear());
        budget.setUser(user);
        this.budgetRepository.setBudget(budget);
        this.moveToHomePage(event);
    }

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
}

