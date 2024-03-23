package org.example;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantsVendor {

    @FXML
    private Label back;

    @FXML
    private ComboBox<String> comboBoxCategory;

    @FXML
    private ComboBox<String> comboBoxSearch;

    @FXML
    private TableColumn<Vendor, String> phoneNumber;

    @FXML
    private TableColumn<Vendor, Integer> price;

    @FXML
    private TextField reserveText;

    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<Vendor> table;

    public void initialize() {
        // Initialize the ComboBox with items
        ObservableList<String> items = FXCollections.observableArrayList("Choose...","DJ", "Dessert", "Decoration", "Catering");
        comboBoxCategory.setItems(items);
        comboBoxCategory.getSelectionModel().select(0);
        ObservableList<String> item = FXCollections.observableArrayList("Choose...","Location", "Price", "Availability");
        comboBoxSearch.setItems(item);
        comboBoxSearch.getSelectionModel().select(0);
    }

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsEventRegistration.fxml"));
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
    void searchClicked(MouseEvent event) {
        String selectedCategory =comboBoxCategory.getValue();
        String selectedSearch = comboBoxSearch.getValue();
        String searchTextValue = searchText.getText();

        if (selectedCategory.equals("Choose...") || selectedSearch.equals("Choose...") || searchTextValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select category, search criteria, and enter search text.");
            alert.showAndWait();
            return;
        }
        String sql = "SELECT Number, Price FROM ServiceProviders WHERE Category = ? AND " + selectedSearch + " = ?";
        ObservableList<Vendor> vendors = FXCollections.observableArrayList();
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, selectedCategory);
            stmt.setString(2, searchTextValue);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Hello");
            while (rs.next()) {
                System.out.println("Hello");
                String phoneNumberValue = rs.getString("Number");
                int priceValue = rs.getInt("Price");
                System.out.println(phoneNumberValue);
                //vendors.add(new Vendor(phoneNumberValue, priceValue));
            }
            table.setItems(vendors);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while retrieving data from the database.");
            alert.showAndWait();
        }
    }
    private String getSelectedComboBoxItem(ComboBox<String> comboBox) {
        // Assuming the ComboBox contains strings
        return comboBox.getSelectionModel().getSelectedItem();
    }


}
