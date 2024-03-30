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
    private TableColumn<EventFinance, String> bookingStatus;

    @FXML
    private TableColumn<EventFinance, String> eventId;

    @FXML
    private TableColumn<EventFinance, String> vendorPayments;

    @FXML
    private TableColumn<EventFinance, String> venueFees;

    @FXML
    private Label back;
    @FXML
    private TextField id2;
    @FXML
    private Button search;
    @FXML
    private TableView<EventFinance> table;

    @FXML
    public void initialize() {
        eventId.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
        vendorPayments.setCellValueFactory(cellData -> cellData.getValue().vendorPaymentsProperty());
        venueFees.setCellValueFactory(cellData -> cellData.getValue().venueRentalFeesProperty());
        bookingStatus.setCellValueFactory(cellData -> cellData.getValue().bookingStatusProperty());
        loadEventData();
    }

    private void loadEventData() {
        executeQuery(null);
    }

    @FXML
    void searchClicked(MouseEvent event) {
        String searchEventId = id2.getText().trim();
        if (!searchEventId.isEmpty()) {
            table.getItems().clear();
            executeQuery(searchEventId);
        } else {
            loadEventData();
        }
    }

    private void executeQuery(String eventID) {
        String sql = buildSQLQuery(eventID);
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = prepareStatement(conn, sql, eventID);
             ResultSet rs = pstmt.executeQuery()) {
            table.getItems().clear();
            while (rs.next()) {
                EventFinance eventFinance = extractEventFinance(rs);
                table.getItems().add(eventFinance);
            }
        } catch (SQLException e) {
            logger.log(null,"An error occurred:",e);
        }
    }


    private String buildSQLQuery(String eventID) {
        String sql = "SELECT e.\"EventName\", " +
                "(SELECT SUM(vb.\"PaymentAmount\") FROM software2024.\"VendorBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VendorPayments, " +
                "(SELECT SUM(vb.\"RentalFee\") FROM software2024.\"VenueBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VenueRentalFees, " +
                "(SELECT STRING_AGG(vb.\"BookingStatus\", ', ') FROM software2024.\"VendorBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VendorBookingStatus, " +
                "(SELECT STRING_AGG(vb.\"BookingStatus\", ', ') FROM software2024.\"VenueBooking\" vb WHERE vb.\"EventID\" = e.\"EventID\") AS VenueBookingStatus " +
                "FROM software2024.\"Events\" e ";
        if (eventID != null) {
            sql += "WHERE e.\"EventID\" = ?";
        }
        return sql;
    }

    private PreparedStatement prepareStatement(Connection conn, String sql, String eventID) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (eventID != null) {
            pstmt.setInt(1, Integer.parseInt(eventID));
        }
        pstmt.close();
        return pstmt;
    }


    private EventFinance extractEventFinance(ResultSet rs) throws SQLException {
        String eventName = rs.getString("EventName");
        String vendorPaymentsAmount = rs.getString("VendorPayments");
        String venueRentalFees = rs.getString("VenueRentalFees");
        String vendorBookingStatus = rs.getString("VendorBookingStatus");
        String venueBookingStatus = rs.getString("VenueBookingStatus");
        String combinedStatus = vendorBookingStatus + "; " + venueBookingStatus;
        return new EventFinance(eventName, vendorPaymentsAmount, venueRentalFees, combinedStatus);
    }

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(null,"An error occurred while opening a new window:");
        }
    }
}
