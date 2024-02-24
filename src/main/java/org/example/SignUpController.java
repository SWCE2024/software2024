package org.example;
import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    static Logger logger = Logger.getLogger(SignUpController.class.getName());

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
            logger.log(null," An error occurred while opening a new window:");
        }
    }

    @FXML
    void signUp2Clicked(ActionEvent event) {

            String userId = id.getText();
            String userPhone = phoneNumber.getText();
            String userEmail = gmail.getText();
            String userUsername = userName.getText();
            String userPassword = password.getText();
            if (!TESTINPUT.idTest(id.getText())) {
                JOptionPane.showMessageDialog(null, "wrong id !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.passwordTest(password.getText())) {
                JOptionPane.showMessageDialog(null, "wrong PASSWORD !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.phoneNumberTest(phoneNumber.getText())) {
                JOptionPane.showMessageDialog(null, "wrong PHONE NUMBER !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.gmailTest(gmail.getText())) {
                JOptionPane.showMessageDialog(null, "wrong GMAIL !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (userId.isEmpty() || userPhone.isEmpty() || userEmail.isEmpty() || userUsername.isEmpty() || userPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field is Empty", em, JOptionPane.ERROR_MESSAGE);
                return;
            }
        try (Connection conn = database.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT CID, USERNAME FROM customer WHERE CID = ? OR USERNAME = ?")) {

            checkStmt.setString(1, userId);
            checkStmt.setString(2, userUsername);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String existingId = rs.getString("CID");
                String existingUsername = rs.getString("USERNAME");

                if (userId.equals(existingId)) {
                    JOptionPane.showMessageDialog(null, "The ID is already in use", em, JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (userUsername.equals(existingUsername)) {
                    JOptionPane.showMessageDialog(null, "The USERNAME is already in use", em, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO CUSTOMER (CID, PHONENUMBER, ADDRESS, GMAIL, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?, ?)")) {
                insertStmt.setString(1, userId);
                insertStmt.setString(2, userPhone);
                insertStmt.setString(3, address.getText());
                insertStmt.setString(4, userEmail);
                insertStmt.setString(5, userUsername);
                insertStmt.setString(6, userPassword);

                int affectedRows = insertStmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Registration successful!", "INSERTED", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed.", em, JOptionPane.ERROR_MESSAGE);
                }
            }

            // Redirect to the next scene
            loadNextScene("/org.example/screen3.fxml");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error: ", e);
            JOptionPane.showMessageDialog(null, "A database error occurred.", em, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadNextScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) signUp2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }
}