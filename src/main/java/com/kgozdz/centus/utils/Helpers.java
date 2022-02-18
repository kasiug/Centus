package com.kgozdz.centus.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Helpers {

    public static int getCurrentYear(){
        return java.time.LocalDate.now().getYear();
    }

    public static int getCurrentMonth(){
        return java.time.LocalDate.now().getMonth().getValue();
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

    public static void moveToHomePage(ActionEvent event, Class className) throws IOException {
        Parent root = FXMLLoader.load(className.getResource("/com/kgozdz/centus/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void onLogOutButtonClick(ActionEvent event, Class className) throws IOException {
        Parent root = FXMLLoader.load(className.getResource("/com/kgozdz/centus/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
