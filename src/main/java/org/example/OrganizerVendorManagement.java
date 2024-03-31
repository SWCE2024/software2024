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

public class OrganizerVendorManagement {

    @FXML
    private Label add;

    @FXML
    private Label back;

    @FXML
    private Label delete;

    @FXML
    private Label update;
    private static final String ERRORMESSEGE = "An error occurred while opening a new window:";
     Parent root;
     Stage stage;
     void callWindow(){
         stage.setScene(new Scene(root));
         stage.show();
         FadeIn fadeIn = new FadeIn(root);
         fadeIn.play();

     }

    @FXML
    void addClicked(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_AddServiceProvider.fxml"));
            stage=(Stage) add.getScene().getWindow();
            callWindow();
        } catch (IOException e) {
            logger.log(Level.SEVERE,ERRORMESSEGE, e);
        }


    }

    @FXML
    void deleteClicked(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_DeleteServiceProvider.fxml"));
            stage=(Stage) delete.getScene().getWindow();
            callWindow();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERRORMESSEGE, e);
        }

    }

    @FXML
    void updateClicked(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/org.example/Organizer_UpdateServiceProvider.fxml"));
            stage=(Stage) update.getScene().getWindow();
            callWindow();

        } catch (IOException e) {
            logger.log(Level.SEVERE, ERRORMESSEGE, e);
        }

    }

    @FXML
    void backClicked(MouseEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
            stage=(Stage) back.getScene().getWindow();
            callWindow();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERRORMESSEGE, e);
        }

    }


}
