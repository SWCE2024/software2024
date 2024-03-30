package org.example;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class MenuAdmin {

    @FXML private Label organizerOperation;

    @FXML private Label report;

    @FXML private Label userManagement;

    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";


    @FXML
    void organizerManagementClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminOrganizerManagement.fxml"));
            Stage stage = (Stage) organizerOperation.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    @FXML
    void reportClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminReport.fxml"));
            Stage stage = (Stage) report.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    @FXML
    void userManagementClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManagement.fxml"));
            Stage stage = (Stage) userManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }

}
