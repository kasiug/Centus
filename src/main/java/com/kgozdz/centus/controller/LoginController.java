package com.kgozdz.centus.controller;

import com.kgozdz.centus.CentusApplication;
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
    private Hyperlink registerUserLink;

    public LoginController() {
        this.userRepository = new UserRepository();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        loginButton.setText("Logowanie...");
        this.userRepository.login(usernameTextField.getText(), passwordTextField.getText());
        if (UserSession.getUserId() > 0){
            Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/home-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            loginButton.setText("Zaloguj");
            String message = "Nieprawidłowa nazwa użytkownika lub hasło";
            errorMessageLabel.setText(message);
        }
    }

    public void onRegisterUserLinkClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/register-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onLoginButtonClick() throws IOException {

    }
}
