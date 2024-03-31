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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class AdminUpdateUser {

    @FXML
    private TextField addressText;

    @FXML
    private Label back;

    @FXML
    private  TextField gmailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private  TextField phoneNumberText;

    @FXML
    private Button updateUser;

    @FXML
    private TextField userIdText;

    @FXML
    private  TextField usernameText;

    @FXML
    private Button get;
    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent parent;
            parent = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManagement.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
            new FadeIn(parent).play();
        }catch (IOException e){
            logger.log(Level.SEVERE,"Error", e);
        }
    }
    Connection connection = Database.connect();
    @FXML
    void getInformation(ActionEvent event){


        String sql = "SELECT \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\" FROM software2024.\"customer\" WHERE \"CID\" = ?";
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(userIdText.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                phoneNumberText.setText(resultSet.getString("PHONENUMBER"));
                addressText.setText(resultSet.getString("ADDRESS"));
                gmailText.setText(resultSet.getString("GMAIL"));
                usernameText.setText(resultSet.getString("USERNAME"));
                passwordText.setText(resultSet.getString("PASSWORD"));
            }else {
                logger.log(Level.SEVERE, "User not found.");

            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);

        }
    }
    @FXML
    void updateUser(ActionEvent event) {
        String sql = "UPDATE software2024.\"customer\" SET \"PHONENUMBER\" = ?, \"ADDRESS\" = ?, \"GMAIL\" = ?, \"USERNAME\" = ?, \"PASSWORD\" = ? WHERE \"CID\" = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1,phoneNumberText.getText());
            preparedStatement.setString(2,addressText.getText());
            preparedStatement.setString(3,gmailText.getText());
            preparedStatement.setString(4,usernameText.getText());
            preparedStatement.setString(5,passwordText.getText());
            preparedStatement.setInt(6, Integer.parseInt(userIdText.getText()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected >0) {
                logger.log(Level.SEVERE,"User information updated successfully.");
            } else {
                logger.log(Level.SEVERE,"Failed to update user information." );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "There is an error", e);
        }

    }
}
