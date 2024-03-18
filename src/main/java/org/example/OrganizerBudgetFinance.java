package org.example;

import animatefx.animation.FadeIn;
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
import static org.example.SignUpController.logger;
public class OrganizerBudgetFinance {
    @FXML
    private Label back;

    @FXML
    private TableColumn<EventFinance, String> eventId;

    @FXML
    private TextField id2;

    @FXML
    private Button search;

    @FXML
    private TableView<EventFinance> table;

    @FXML
    private TableColumn<EventFinance, String> vendorPayments;

    @FXML
    private TableColumn<EventFinance, String> venueFees;

    @FXML
    public void initialize() {
        eventId.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
        vendorPayments.setCellValueFactory(cellData -> cellData.getValue().vendorPaymentsProperty());
        venueFees.setCellValueFactory(cellData -> cellData.getValue().venueRentalFeesProperty());
        loadEventData();
    }

    private void loadEventData() {
        String sql = "SELECT e.\"EventName\", " +
                "(SELECT SUM(vb.\"PaymentAmount\") FROM software2024.\"VendorBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VendorPayments, " +
                "(SELECT SUM(vb.\"RentalFee\") FROM software2024.\"VenueBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VenueRentalFees " +
                "FROM software2024.\"Events\" e";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            table.getItems().clear();
            while (rs.next()) {
                String eventName = rs.getString("EventName");
                String vendorPayments = rs.getString("VendorPayments");
                String venueRentalFees = rs.getString("VenueRentalFees");
                EventFinance eventFinance = new EventFinance(eventName, vendorPayments, venueRentalFees);
                table.getItems().add(eventFinance);
            }
        } catch (SQLException e) {
            logger.log(null," An error occurred while opening a new window:");
            e.printStackTrace();
        }
    }
    @FXML
    void searchClicked(MouseEvent event) {
        if (!id2.getText().trim().isEmpty()) {
            table.getItems().clear();
            loadSingleEvent(id2.getText().trim());
        } else {
            loadEventData();
        }
    }

    private void loadSingleEvent(String eventID) {
        String sql = "SELECT e.\"EventName\", " +
                "(SELECT SUM(vb.\"PaymentAmount\") FROM software2024.\"VendorBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VendorPayments, " +
                "(SELECT SUM(vb.\"RentalFee\") FROM software2024.\"VenueBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VenueRentalFees " +
                "FROM software2024.\"Events\" e " +
                "WHERE e.\"EventID\" = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(eventID));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String eventName = rs.getString("EventName");
                String vendorPayments = rs.getString("VendorPayments");
                String venueRentalFees = rs.getString("VenueRentalFees");
                EventFinance eventFinance = new EventFinance(eventName, vendorPayments, venueRentalFees);
                table.getItems().add(eventFinance);
            }
        } catch (SQLException e) {
            logger.log(null," An error occurred while opening a new window:");
            e.printStackTrace();
        }
    }
    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(null," An error occurred while opening a new window:");
        }
    }
}
