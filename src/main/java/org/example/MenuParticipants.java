package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import animatefx.animation.FadeIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.*;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class MenuParticipants {

    @FXML private Label directCommunication;
    @FXML private Label eventCalender;
    @FXML private Label eventRegistering;
    @FXML private Label ticketManagement;
    private String em="successful";

    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";

    @FXML
    void directCommunicationClicked(MouseEvent event) {
        String participantEmail = HelloController.getEmail();
        String subject = "YOUR EVENT Application";
        String messageText = "We are happy that you chose our store. Please let us know if you have any questions";
        if (participantEmail != null && !participantEmail.isEmpty()) {
            try {
                EmailUtil.sendEmail(participantEmail, subject, messageText);
                logger.log(Level.SEVERE, "Email sent successfully to " + participantEmail);
                JOptionPane.showMessageDialog(null, "Email sent successfully.", em, JOptionPane.INFORMATION_MESSAGE);
            } catch (MessagingException e) {
                logger.log(Level.SEVERE, "Could not send email to " + participantEmail, e);
            }
        } else {
            logger.log(Level.SEVERE, "No email address available for the current participant.");

        }
    }
    @FXML
    void eventCalenderClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/calender.fxml"));
            Stage stage = (Stage) eventCalender.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    @FXML
    void eventRegisteringClicked(MouseEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsEventRegistering.fxml"));
            Stage stage = (Stage) eventRegistering.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    @FXML
    void ticketManagementClicked(MouseEvent event)
    {
        String participantEmailForTicket = HelloController.getEmail();
        String messageText =Database.GetParticipantMessageTicket();
        String subject = Database.getSubject();

            try
            {
                EmailTicket.sendEmail(participantEmailForTicket, subject, messageText);
                logger.log(Level.SEVERE, "Email sent successfully to " + participantEmailForTicket);

                JOptionPane.showMessageDialog(null, "Email sent successfully.", em, JOptionPane.INFORMATION_MESSAGE);
            }
            catch (MessagingException e)
            {
                logger.log(Level.SEVERE, "Could not send email to " + participantEmailForTicket, e);

            }



    }




}
