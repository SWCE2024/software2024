package org.example;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import static org.example.SignUpController.logger;

public class HelloController {

    @FXML
    private TextField gmailLogIn;

    @FXML
    private Button login1;

    @FXML
    private PasswordField passwordLogIn;
    @FXML
    private Button signUp;
    @FXML
    private Button start;
    @FXML
    public void login1Clicked(ActionEvent event) {
        String email = gmailLogIn.getText();
        String password = passwordLogIn.getText();
        try {

            if (Database.validateLogin(email, password, "ADMIN")) {
                System.out.println("login admin successfully.");
                Parent root;
                FXMLLoader fxmlLoader;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
            } else if (Database.validateLogin(email, password, "customer")) {
                System.out.println("login customer successfully.");
                Parent root;
                FXMLLoader fxmlLoader;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuParticipants.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
            }
            else if (Database.validateLogin(email, password, "organizer")) {
                System.out.println("login organizer successfully.");
                Parent root;
                FXMLLoader fxmlLoader;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
            } else {
                System.out.println("please signup");
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }

    @FXML
    void signUp1Clicked(ActionEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/SignUp.fxml"));
            Stage stage = (Stage) signUp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }

    public void startClicked(MouseEvent mouseEvent) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/login.fxml"));
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }
}
