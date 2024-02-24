package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class MenuParticipants {

    @FXML
    private Label DirectCommunication;

    @FXML
    private Label EventCalender;

    @FXML
    private Label EventRegistering;

    @FXML
    private Label TicketManagement;

    @FXML
    void DirectCommunicationClicked(MouseEvent event) {


    }

    @FXML
    void EventCalenderClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/calender.fxml"));
            Stage stage = (Stage) EventCalender.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EventRegisteringClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsEventRegistering.fxml"));
            Stage stage = (Stage) EventRegistering.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void TicketManagementClicked(MouseEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsTicketManagement.fxml"));
            Stage stage = (Stage) TicketManagement.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
