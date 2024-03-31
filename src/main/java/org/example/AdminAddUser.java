package org.example;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class AdminAddUser {

    @FXML
    private Button addUser;

    @FXML
    private TextField addressText;

    @FXML
    private Label back;

    @FXML
    private TextField gmailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField usernameText;


    @FXML
    void addNewUser(ActionEvent event) {
                // User details
                //String phoneNumber = phoneNumberText.getText();
                //String address = addressText.getText();
                //String gmail = gmailText.getText();
                //String username = usernameText.getText();
                //String password = passwordText.getText();

                // SQL statement to insert user into customer table
                String sql = "INSERT INTO software2024.\"customer\" (\"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?)";
                try (
                        Connection connection = Database.connect();
                        PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
                    // Setting parameter values
                    preparedStatement.setString(1, phoneNumberText.getText());
                    preparedStatement.setString(2, addressText.getText());
                    preparedStatement.setString(3, gmailText.getText());
                    preparedStatement.setString(4, usernameText.getText());
                    preparedStatement.setString(5, passwordText.getText());
                    int rowsAffected = preparedStatement.executeUpdate();

                        logger.log(Level.SEVERE,"User added successfully.");
                } catch (SQLException e) {

                    logger.log(Level.SEVERE, "Failed to add user.");
                }
    }

    @FXML
    void backClicked(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManagement.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(Level.SEVERE,"Can't open a window" , e);
        }

    }
}
