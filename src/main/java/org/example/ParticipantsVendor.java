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
import java.util.logging.Level;

import static org.example.SignUpController.logger;

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
    private String choose="Choose...";
    private String priceName="Price";

    public void initialize() {
        // Initialize the ComboBox with items
        ObservableList<String> items = FXCollections.observableArrayList(choose,"DJ", "Dessert", "Decoration", "Catering");
        comboBoxCategory.setItems(items);
        comboBoxCategory.getSelectionModel().select(0);
        ObservableList<String> item = FXCollections.observableArrayList(choose,"Location", priceName, "Availability");
        comboBoxSearch.setItems(item);
        comboBoxSearch.getSelectionModel().select(0);
    }

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsEventRegistering.fxml"));
            Stage stage=(Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }

    }
    @FXML
    void reserveClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Messeage");
        alert.setHeaderText(null);
        alert.setContentText("Reserve Successfully");
        alert.showAndWait();
    }
    @FXML
    void searchClicked(MouseEvent event) throws SQLException {
        String selectedCategory =getSelectedComboBoxItem(comboBoxCategory);
        String selectedSearch = getSelectedComboBoxItem(comboBoxSearch);
        String searchTextValue = searchText.getText();
        Connection conn = Database.connect();
        String sql = null;
        ObservableList<Vendor> vendors = FXCollections.observableArrayList();
        phoneNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        PreparedStatement stmt=null;

        if (selectedCategory.equals(choose) || selectedSearch.equals(choose) || searchTextValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select category, search criteria, and enter search text.");
            alert.showAndWait();
        }
        if(selectedSearch.equals(priceName)){
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Price\" = ?";
              //  SELECT "Number", "Price" FROM software2024."ServiceProviders" WHERE "Category" = ? AND "Price" = ?
            }
        if (selectedSearch.equals("Location")) {
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Location\" = ?";
            }
        if (selectedSearch.equals("Availability")){
                sql = "SELECT \"Number\", \"Price\" FROM software2024.\"ServiceProviders\" WHERE \"Category\" = ? AND \"Availability\" = ?";
            }
        try{
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, selectedCategory);
            if (selectedSearch.equals(priceName)) {
                stmt.setInt(2, Integer.parseInt(searchTextValue));
            } else {
                stmt.setString(2, searchTextValue);
            }
                 ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String phoneNumberValue = rs.getString("Number");
                    int priceValue = rs.getInt(priceName);
                    vendors.add(new Vendor(phoneNumberValue, priceValue));
                }
                table.setItems(vendors);
            } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error ", e);
            }finally {
                  if (stmt != null){
                        stmt.close();}
            }

    }
    private String getSelectedComboBoxItem(ComboBox<String> comboBox) {
        // Assuming the ComboBox contains strings
        return comboBox.getSelectionModel().getSelectedItem();
    }


}
