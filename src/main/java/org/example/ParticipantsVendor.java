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
    private TableColumn<Vendor, Double> price;
    @FXML
    private Button reserve;

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
    void reserveClicked(MouseEvent event) {



    }
    @FXML
    void searchClicked(MouseEvent event) throws SQLException {
        String selectedCategory =getSelectedComboBoxItem(comboBoxCategory);
        String selectedSearch = getSelectedComboBoxItem(comboBoxSearch);
        String searchTextValue = searchText.getText();

        if (selectedCategory.equals("Choose...") || selectedSearch.equals("Choose...") || searchTextValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select category, search criteria, and enter search text.");
            alert.showAndWait();
            return;
        }else {
            Connection conn = Database.connect();
            String sql = null;

            if(selectedSearch=="Price"){
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Price\" = ?";
              //  SELECT "Number", "Price" FROM software2024."ServiceProviders" WHERE "Category" = ? AND "Price" = ?
            }
            else if (selectedSearch=="Location") {
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Location\" = ?";
            }else {
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Availability\" = ?";
            }
            ObservableList<Vendor> vendors = FXCollections.observableArrayList();
            phoneNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
            price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

            try{
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, selectedCategory);
                if(selectedSearch=="Price")
                stmt.setInt(2, Integer.parseInt(searchTextValue));
                else
                    stmt.setString(2, searchTextValue);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String phoneNumberValue = rs.getString("Number");
                    int priceValue = rs.getInt("Price");
                    vendors.add(new Vendor(phoneNumberValue, priceValue));
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
    }
    private String getSelectedComboBoxItem(ComboBox<String> comboBox) {
        // Assuming the ComboBox contains strings
        return comboBox.getSelectionModel().getSelectedItem();
    }


}
