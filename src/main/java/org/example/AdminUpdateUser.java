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
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManegement.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(Level.SEVERE, "An error", e);
        }
    }


    Connection connection = Database.connect();
    private String phoneNumber;
    private String address;
    private String gmail;
    private String username;
    private String password;
    private String ID = userIdText.getText();

    @FXML
    void get_information(ActionEvent event){

        String sql = "SELECT \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\" FROM software2024.\"customer\" WHERE \"CID\" = ?";
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(ID));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieving user information from the database
                 phoneNumber = resultSet.getString("PHONENUMBER");
                 address = resultSet.getString("ADDRESS");
                 gmail = resultSet.getString("GMAIL");
                 username = resultSet.getString("USERNAME");
                 password = resultSet.getString("PASSWORD");

                phoneNumberText.setText(phoneNumber);
                addressText.setText(address);
                gmailText.setText(gmail);
                usernameText.setText(username);
                passwordText.setText(password);
            }else {
                logger.log(Level.SEVERE, "User not found.");

            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);

        }



    }

    @FXML
    void update_User(ActionEvent event) {
        String sql = "UPDATE software2024.\"customer\" SET \"PHONENUMBER\" = ?, \"ADDRESS\" = ?, \"GMAIL\" = ?, \"USERNAME\" = ?, \"PASSWORD\" = ? WHERE \"CID\" = ?";
        phoneNumber = phoneNumberText.getText();
        address = addressText.getText();
        gmail = gmailText.getText();
        username = usernameText.getText();
        password = passwordText.getText();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1,phoneNumber);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,gmail);
            preparedStatement.setString(4,username);
            preparedStatement.setString(5,password);
            preparedStatement.setInt(6, Integer.parseInt(ID));
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
