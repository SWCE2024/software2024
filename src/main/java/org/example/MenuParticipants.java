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
import java.util.List;

public class MenuParticipants {

    @FXML private Label DirectCommunication;
    @FXML private Label EventCalender;
    @FXML private Label EventRegistering;
    @FXML private Label TicketManagement;
    private String em="successful";
    @FXML
    void DirectCommunicationClicked(MouseEvent event) {
        String participantEmail = HelloController.getEmail();
        String subject = "YOUR EVENT Application";
        String messageText = "We are happy that you chose our store. Please let us know if you have any questions";
        if (participantEmail != null && !participantEmail.isEmpty()) {
            try {
                EmailUtil.sendEmail(participantEmail, subject, messageText);
                System.out.println("Email sent successfully to " + participantEmail);
                JOptionPane.showMessageDialog(null, "Email sent successfully.", em, JOptionPane.INFORMATION_MESSAGE);
            } catch (MessagingException e) {
                System.out.println("Could not send email to " + participantEmail);
                e.printStackTrace();
            }
        } else {
            // Optionally handle the case where there is no email address to send to.
            System.out.println("No email address available for the current participant.");
        }
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
    void TicketManagementClicked(MouseEvent event)
    {
        String participantEmailForTicket = HelloController.getEmail();
        String messageText =Database.GetParticipantMessageTicket(); ;
        String subject = Database.getSubject();

            try
            {
                EmailTicket.sendEmail(participantEmailForTicket, subject, messageText);
                System.out.println("Email sent successfully to " + participantEmailForTicket);
                JOptionPane.showMessageDialog(null, "Email sent successfully.", em, JOptionPane.INFORMATION_MESSAGE);
            }
            catch (MessagingException e)
            {
                System.out.println("Could not send email to " + participantEmailForTicket);
                e.printStackTrace();
            }



    }




}
