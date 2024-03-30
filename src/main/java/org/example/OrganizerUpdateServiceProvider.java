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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class OrganizerUpdateServiceProvider {
    @FXML
    private TextField availableText;

    @FXML
    private Label back;

    @FXML
    private TextField categoryText;

    @FXML
    private Button get;

    @FXML
    private TextField locationText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField serviceProviderIdText;

    @FXML
    private Button update;

    @FXML
    private TextField userIDText;

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVendorManagement.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(Level.SEVERE, "An error", e);
        }
    }

    Connection connection = Database.connect();

    @FXML
    void getInformation(ActionEvent event) {
        String sql = "SELECT \"Number\", \"Category\", \"Location\", \"Price\", \"Availability\", \"userID\" FROM software2024.\"ServiceProviders\" WHERE \"ServiceProviderID\" = ?";

        String id = serviceProviderIdText.getText();

        try (

                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieving user information from the database
                String numberS = resultSet.getString("Number");
                String categoryS = resultSet.getString("Category");
                String locationS = resultSet.getString("Location");
                int priceS = resultSet.getInt("Price");
                String avaS = resultSet.getString("Availability");
                int useridS = resultSet.getInt("userID");

                phoneNumberText.setText(numberS);
                categoryText.setText(categoryS);
                locationText.setText(locationS);
                priceText.setText(String.valueOf(priceS));
                availableText.setText(avaS);
                userIDText.setText(String.valueOf(useridS));
            } else {
                logger.log(Level.SEVERE, "Service Provider not found.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error happened", e);
        }

    }

    @FXML
    void updateClicked(ActionEvent event) {
        String sql = "UPDATE software2024.\"ServiceProviders\" SET \"Number\" = ?, \"Category\" = ?, \"Location\" = ?, \"Price\" = ?, \"Availability\" = ?, \"userID\" = ? WHERE \"ServiceProviderID\" = ?";
        String phoneNumber = phoneNumberText.getText();
        String category = categoryText.getText();
        String location = locationText.getText();
        String price = priceText.getText();
        String available = availableText.getText();
        String userid = userIDText.getText();
        String serviceProviderID = serviceProviderIdText.getText();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, Integer.parseInt(price));
            preparedStatement.setString(5, available);
            preparedStatement.setInt(6, Integer.parseInt(userid));
            preparedStatement.setInt(7, Integer.parseInt(serviceProviderID));
            // Executing the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                logger.log(Level.SEVERE, "Service Provider information updated successfully.");

            } else {
                logger.log(Level.SEVERE, "Failed to update Service Provider information.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }
}


