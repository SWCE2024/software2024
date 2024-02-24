package org.example;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuOrganizer {

    @FXML
    private Label BudgetFinance;

    @FXML
    private Label VendorManagement;

    @FXML
    private Label VenueManagement;

    @FXML
    private Label calender;

    @FXML
    private Label eventManagement;

    @FXML
    void BudgetFinanceClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerBudgetFinance.fxml"));
            Stage stage = (Stage) BudgetFinance.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void VendorManagementClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVendorManagement.fxml"));
            Stage stage = (Stage) VendorManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void VenueManagementClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVenueManagement.fxml"));
            Stage stage = (Stage) VenueManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void calender(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/calender.fxml"));
            Stage stage = (Stage) calender.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void eventManagementClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerEventManagement.fxml"));
            Stage stage = (Stage) eventManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
