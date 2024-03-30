package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class OrganizerVendorManagement {

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
            AdminUserManagement.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_AddServiceProvider.fxml"));
            AdminUserManagement.stage=(Stage) Add.getScene().getWindow();
            AdminUserManagement.callScreen();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }


    }

    @FXML
    void DeleteClicked(MouseEvent event) {
        try {
            AdminUserManagement.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_DeleteServiceProvider.fxml"));
            AdminUserManagement.stage=(Stage) delete.getScene().getWindow();
            AdminUserManagement.callScreen();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void UpdateClicked(MouseEvent event) {
        try {
            AdminUserManagement.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_UpdateServiceProvider.fxml"));
            AdminUserManagement.stage=(Stage) update.getScene().getWindow();
            AdminUserManagement.callScreen();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void backClicked(MouseEvent event) {
        try {

            AdminUserManagement.root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
            AdminUserManagement.stage=(Stage) back.getScene().getWindow();
            AdminUserManagement.callScreen();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }


}
