package com.kgozdz.centus.controller;

import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    private IUserRepository userRepository;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink forgotPasswordLink;

    public LoginController() {
        this.userRepository = new UserRepository();
    }

    @FXML
    protected void onLoginButtonClick() {
        boolean isLogged = this.userRepository.login(usernameTextField.getText(), passwordTextField.getText());
        String message = isLogged? "Logged": "Invalid Username or password";

        errorMessageLabel.setText(message);
    }

    @FXML
    protected void onForgotPasswordLinkClick() {
        errorMessageLabel.setText("Forgot password Clicked");
    }
}
