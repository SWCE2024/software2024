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

public class AdminDeleteUser {

    @FXML
    private Label back;

    @FXML
    private Button deleteUser;

    @FXML
    private TextField userIdText;

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/AdminUserManagement.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void delete_User(ActionEvent event) {
        String sql = "DELETE FROM software2024.\"customer\" WHERE \"CID\" = ?";
        String CID = userIdText.getText();

        try (
                Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(CID));

            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user. User with ID " + CID + " not found.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete Faild.");
        }

    }

}
