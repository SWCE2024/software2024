package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class Organizer_AddServiceProvider {

    @FXML
    private Button addService;

    @FXML
    private Label back;

    @FXML
    private TextField categoryText;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private RadioButton off;

    @FXML
    private RadioButton on;

    private ToggleGroup toggleGroup;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField priceText;

    public void initialize() {
        // Create a toggle group and add radio buttons to it
        toggleGroup = new ToggleGroup();
        off.setToggleGroup(toggleGroup);
        on.setToggleGroup(toggleGroup);
        // Initialize the ComboBox with items
        ObservableList<String> item = FXCollections.observableArrayList("Choose...","Nablus", "Bethlehem", "Ramallah", "Jericho");
        comboBox.setItems(item);
        // Set an initial selection if needed
        comboBox.getSelectionModel().select(0);
    }
    private static String availability;

    @FXML
    private String handleRadioButtonAction() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (selectedRadioButton == off) {
                availability="OFF";

            } else if (selectedRadioButton == on) {
                availability="ON";

            }

        return availability;
    }
    private String getSelectedComboBoxItem(ComboBox<String> comboBox) {
        // Assuming the ComboBox contains strings
        return comboBox.getSelectionModel().getSelectedItem();
    }
    @FXML
    void addClicked(ActionEvent event) throws SQLException {
        String category = categoryText.getText();
        String phoneNumber = phoneNumberText.getText();
        String price = priceText.getText();
        String comboBoxValue = getSelectedComboBoxItem(comboBox);
        String availble = handleRadioButtonAction();


        if (TESTINPUT.checkInputs(phoneNumber, price))
        {
            Connection con = Database.connect();
            PreparedStatement preparedStatement=null;
            try {
                String sql = "INSERT INTO software2024.\"ServiceProviders\" (\"Number\",\"Category\",\"Location\",\"Price\",\"Availability\",\"userID\") VALUES (?,?,?,?,?,?)";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, phoneNumber);  // Assuming CID is an integer
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, comboBoxValue);
                preparedStatement.setInt(4, Integer.parseInt(price));
                preparedStatement.setString(5,availble);
                preparedStatement.setInt(6, Integer.parseInt(Database.getUserID()));
                preparedStatement. executeUpdate();
                JOptionPane.showMessageDialog(null, "Done", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "An error happened", e);
            }finally {
                if (preparedStatement != null) {
                        preparedStatement.close();

                }
            }

        }

    }
    AdminUserManagement admin = new AdminUserManagement();

    @FXML
    void backClicked(MouseEvent event) {
        try {

            admin.root = FXMLLoader.load(getClass().getResource("/org.example/OrganizerVendorManagement.fxml"));
            admin.stage=(Stage) back.getScene().getWindow();
            admin.callScreen();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }

    }

}
