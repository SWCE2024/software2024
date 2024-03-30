package org.example;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class AdminUserManagement {

    @FXML
    private Label Add;

    @FXML
    private Label back;

    @FXML
    private Label delete;

    @FXML
    private Label update;
    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";

    @FXML
    void AddClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminAddUser.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {

            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void DeleteClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminDeleteUser.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {

            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void UpdateClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminUpdateUser.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {

            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {

            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

}
