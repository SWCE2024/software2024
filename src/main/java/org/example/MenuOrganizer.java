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

public class MenuOrganizer {

    @FXML
    private Label budgetFinance;

    @FXML
    private Label vendorManagement;

    @FXML
    private Label venueManagement;

    @FXML
    private Label calender;

    @FXML
    private Label eventManagement;

    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";

    @FXML
    void budgetFinanceClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerBudgetFinance.fxml"));
            Stage stage = (Stage) budgetFinance.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void vendorManagementClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVendorManagement.fxml"));
            Stage stage = (Stage) vendorManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void venueManagementClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVenueManagement.fxml"));
            Stage stage = (Stage) venueManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void calender(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/calender.fxml"));
            Stage stage = (Stage) calender.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void eventManagementClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerEventManagement.fxml"));
            Stage stage = (Stage) eventManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

}
