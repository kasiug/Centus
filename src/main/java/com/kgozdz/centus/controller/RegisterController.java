package com.kgozdz.centus.controller;

import com.kgozdz.centus.model.User;
import com.kgozdz.centus.repository.IUserRepository;
import com.kgozdz.centus.repository.implementation.UserRepository;
import com.kgozdz.centus.utils.Cryptography;
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
import java.security.NoSuchAlgorithmException;

public class RegisterController {

    private IUserRepository userRepository;

    @FXML
    private Label userExistErrorMessage;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField repeatPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Hyperlink exitToLoginPageLink;

    public RegisterController() {
        this.userRepository = new UserRepository();
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        if (emailTextField.getText() != "" && usernameTextField.getText() != "" && passwordTextField.getText() != "" && repeatPasswordField.getText() != "" && firstNameField.getText() !="" && lastNameField.getText() !="") {
            if (passwordTextField.getText().equals(repeatPasswordField.getText()) ) {
                User user = new User();
                user.setFirstName(firstNameField.getText());
                user.setLastName(lastNameField.getText());
                user.setEmail(emailTextField.getText());
                user.setUserName(usernameTextField.getText());
                try {
                    user.setPassword(Cryptography.getPasswordHash(passwordTextField.getText()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                this.userRepository.register(user);
                this.moveToLoginPage(event);
            } else{
                userExistErrorMessage.setText("Hasła nie są takie same");
            }
        }
        else{
            userExistErrorMessage.setText("Wypełnij wszystkie pola!");
        }
    }


    @FXML
    protected void onExitToLoginPageLinkClick(ActionEvent event) throws IOException {
     this.moveToLoginPage(event);
    }

    private void moveToLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kgozdz/centus/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
