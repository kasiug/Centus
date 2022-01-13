package com.kgozdz.centus;

import com.kgozdz.centus.model.User;
import com.kgozdz.centus.utils.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.lang.module.Configuration;
import java.sql.*;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Centus!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/centus", "root", "Localhost123!");
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from users");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("firstname"));
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<User> result = session.createQuery("from User", User.class).list();

        result.forEach(person -> {
            System.out.println(person.getEmail());

            person.getBudgets().forEach(budget ->
                    System.out.println("    budget for " + budget.month + "." + budget.year + " is: " + budget.amount));
        });
        session.getTransaction().commit();


        HibernateUtil.shutdown();

//        launch();
    }
}