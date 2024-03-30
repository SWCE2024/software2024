package org.example;
import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.*;
import javafx.scene.control.TextField;


public class SignUpController {
    public static Logger logger = Logger.getLogger(SignUpController.class.getName());
    @FXML
    private TextField address;
    @FXML
    private TextField userName;
    @FXML
    private TextField gmail;
    @FXML
    private TextField id;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private Label back;
    @FXML
    private Button signUp2;
    private String em="ERROR";
    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/hello-view.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }
    @FXML
    void signUp2Clicked(ActionEvent event) {
        String idText = id.getText();
        String phoneNumberText = phoneNumber.getText();
        String addressText = address.getText();
        String gmailText = gmail.getText();
        String userNameText = userName.getText();
        String passwordText = password.getText();
        boolean validId = TESTINPUT.idTest(idText);
        boolean validPhone = TESTINPUT.phoneNumberTest(phoneNumberText);
        boolean validGmail = TESTINPUT.gmailTest(gmailText);
        boolean validPassword = TESTINPUT.passwordTest(passwordText);
        if (validId && validPhone && validGmail && validPassword) {
            boolean isRegistered = Database.registerCustomer(idText, phoneNumberText, addressText, gmailText, userNameText, passwordText);
            if (isRegistered) {
                loadMenuParticipants();
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed. Please check the information provided and try again.", em, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid information in all fields.", em, JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadMenuParticipants() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/MenuParticipants.fxml"));
            Stage stage = (Stage) signUp2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}