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

public class Organizer_UpdateServiceProvider {
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
            root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVendorManegement.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    Connection connection = Database.connect();

    @FXML
    void get_information(ActionEvent event) {
        String sql = "SELECT \"Number\", \"Category\", \"Location\", \"Price\", \"Availability\", \"userID\" FROM software2024.\"ServiceProviders\" WHERE \"ServiceProviderID\" = ?";

        String ID = serviceProviderIdText.getText();

        try (

                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, Integer.parseInt(ID));
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
                System.out.println("Service Provider not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get Faild.");
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
        String ServiceProviderID = serviceProviderIdText.getText();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, Integer.parseInt(price));
            preparedStatement.setString(5, available);
            preparedStatement.setInt(6, Integer.parseInt(userid));
            preparedStatement.setInt(7, Integer.parseInt(ServiceProviderID));
            // Executing the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Service Provider information updated successfully.");
            } else {
                System.out.println("Failed to update Service Provider information.");
            }
        } catch (SQLException e) {
            System.out.println("Update Faild");
            e.printStackTrace();
        }
    }
}


