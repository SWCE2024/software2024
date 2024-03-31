package org.example;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class OrganizerEventManagement implements Initializable {

    @FXML
    private Button updateProduct1;

    @FXML
    private Button addEvent;

    @FXML
    private Button addProduct1;

    @FXML
    private VBox addProductBox;

    @FXML
    private Label back;

    @FXML
    private TextField dateAdd1;

    @FXML
    private TextField dateUpdate1;

    @FXML
    private HBox deleteBox;

    @FXML
    private Button deleteEvent;

    @FXML
    private Button deleteEvent1;

    @FXML
    private TextField discriptionAdd11;

    @FXML
    private TextField eventNameAdd1;

    @FXML
    private TextField eventNameDelete1;

    @FXML
    private TextField eventNameUpdate1;

    @FXML
    private TextField eventNameUpdate1After;

    @FXML
    private Button gitInformation;

    @FXML
    private TextField loacationAdd1;

    @FXML
    private TextField locationUpdate1;

    @FXML
    private TextField timeAdd1;

    @FXML
    private TextField timeUpdate1;

    @FXML
    private Button updateEvent;

    @FXML
    private VBox updateProductBox;

    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";
    private static final String ERROR_MESSAGE = "An error occurred.";
    private static final String ERROR_TITLE = "ERROR";

    @FXML
    void updateProduct1Clicked(MouseEvent event) {
        updateEvent(eventNameUpdate1.getText(), eventNameUpdate1After.getText(), dateUpdate1.getText(), locationUpdate1.getText(), timeUpdate1.getText());
    }

    @FXML
    void addClicked(MouseEvent event) {
        setVisibility(true, false, false);
    }

    @FXML
    void addProduct1Clicked(MouseEvent event) {
        addEvent(eventNameAdd1.getText(), dateAdd1.getText(), loacationAdd1.getText(), timeAdd1.getText(), discriptionAdd11.getText());
    }

    @FXML
    void backClicked(MouseEvent event) {
        changeScene("/org.example/MenuOrganizer.fxml");
    }

    @FXML
    void deleteClicked(MouseEvent event) {
        setVisibility(false, true, false);
    }

    @FXML
    void gitInformationClicked(MouseEvent event) {
        getEventInformation(eventNameUpdate1.getText());
    }

    @FXML
    void updateClicked(MouseEvent event) {
        setVisibility(false, false, true);
    }

    @FXML
    void eventDelete2Clicked(MouseEvent event) {
        deleteEvent(eventNameDelete1.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibility(false, false, false);
    }

    private void executeDBUpdate(String sql, PreparedStatementSetter setter) {
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setter.setValues(pstmt);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Operation Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }


    private void addEvent(String name, String date, String location, String time, String description) {
        String sql = "INSERT INTO software2024.\"EVENT\" (\"EventName\", \"EventDate\", \"EventLocation\", \"EventTime\", \"EventDiscription\") VALUES (?, ?, ?, ?, ?)";
        executeDBUpdate(sql, pstmt -> {
            pstmt.setString(1, name);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setString(3, location);
            pstmt.setString(4, time);
            pstmt.setString(5, description);
        });
    }

    private void updateEvent(String oldName, String newName, String date, String location, String time) {
        String sql = "UPDATE software2024.\"EVENT\" SET \"EventName\" = ?, \"EventDate\" = ?, \"EventLocation\" = ?, \"EventTime\" = ? WHERE \"EventName\" = ?";
        executeDBUpdate(sql, pstmt -> {
            pstmt.setString(1, newName);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setString(3, location);
            pstmt.setString(4, time);
            pstmt.setString(5, oldName);
        });
    }

    private void deleteEvent(String name) {
        String sql = "DELETE FROM software2024.\"EVENT\" WHERE \"EventName\" = ?";
        executeDBUpdate(sql, pstmt -> pstmt.setString(1, name));
    }

    private void getEventInformation(String name) {
        String sql = "SELECT \"EventName\", \"EventDate\", \"EventLocation\", \"EventTime\", \"EventDiscription\" FROM software2024.\"EVENT\" WHERE \"EventName\" = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                eventNameUpdate1After.setText(rs.getString("EventName"));
                dateUpdate1.setText(rs.getDate("EventDate").toString());
                locationUpdate1.setText(rs.getString("EventLocation"));
                timeUpdate1.setText(rs.getString("EventTime"));
                // Assuming there's a textField for description in your UI which I missed earlier.
                discriptionAdd11.setText(rs.getString("EventDiscription"));
            } else {
                JOptionPane.showMessageDialog(null, "Event not found.", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }


    private void setVisibility(boolean addBox, boolean deleteBox, boolean updateBox) {
        this.addProductBox.setVisible(addBox);
        this.deleteBox.setVisible(deleteBox);
        this.updateProductBox.setVisible(updateBox);
    }

    private void changeScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }

    @FunctionalInterface
    interface PreparedStatementSetter {
        void setValues(PreparedStatement pstmt) throws SQLException;
    }
}
