package org.example;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class AdminDeleteUser {

    @FXML
    private Label back;

    @FXML
    private Button deleteUser;

    @FXML
    private TextField userIdText;
    private String errorMessege="Faild to open Window";

    @FXML
    void backWindow(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManagement.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(Level.SEVERE,errorMessege , e);
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        String sql = "DELETE FROM software2024.\"customer\" WHERE \"CID\" = ?";
        String customerID = userIdText.getText();

        try (
                Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(customerID));

            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                logger.log(Level.SEVERE,"User deleted successfully.");
            } else {
                logger.log(Level.SEVERE, "Failed to delete user. User with ID {0} not found.", customerID);

            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, errorMessege, e);
        }

    }

}
