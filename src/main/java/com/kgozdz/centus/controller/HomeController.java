package com.kgozdz.centus.controller;

import com.kgozdz.centus.UserSession;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
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
import java.net.URL;

public class HomeController {
    private IUserRepository userRepository;

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
    }

    @FXML
    protected void initialize() {
        logOutButton.setText("Wyloguj "+ UserSession.getUserName());
    }

//    @FXML
//    protected void onLogOutButtonClick() {
//        boolean isLogged = this.userRepository.login(usernameTextField.getText(), passwordTextField.getText());
//        String message = isLogged ? "Logged" : "Invalid Username or password";
//
//        errorMessageLabel.setText(message);
//    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        UserSession.cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
}
