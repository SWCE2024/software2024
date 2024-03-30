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
     AdminUserManagement admin = new AdminUserManagement();

    @FXML
    void AddClicked(MouseEvent event) {
        try {
            admin.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_AddServiceProvider.fxml"));
            admin.stage=(Stage) Add.getScene().getWindow();
            admin.callScreen();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }


    }

    @FXML
    void DeleteClicked(MouseEvent event) {
        try {
            admin.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_DeleteServiceProvider.fxml"));
            admin.stage=(Stage) delete.getScene().getWindow();
            admin.callScreen();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void UpdateClicked(MouseEvent event) {
        try {
            admin.root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_UpdateServiceProvider.fxml"));
            admin.stage=(Stage) update.getScene().getWindow();
            admin.callScreen();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }

    @FXML
    void backClicked(MouseEvent event) {
        try {

            admin.root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
            admin.stage=(Stage) back.getScene().getWindow();
            admin.callScreen();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }

    }


}
