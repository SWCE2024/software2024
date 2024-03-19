package org.example;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import static org.example.SignUpController.logger;

public class AdminReport {
    @FXML private Label back;
    @FXML private TableColumn<CustomerTotal, String> customerId;
    @FXML private TableColumn<CustomerTotal, Double> totalAmountOfMoney;
    @FXML private TableView<CustomerTotal> reportTable;
    @FXML
    public void initialize() {
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        totalAmountOfMoney.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        reportTable.setItems(getCustomerTotals());
    }
    private ObservableList<CustomerTotal> getCustomerTotals() {
        ObservableList<CustomerTotal> customerTotals = FXCollections.observableArrayList();
        String sql = "SELECT c.\"CID\", COALESCE(SUM(vb.\"PaymentAmount\"), 0) + COALESCE(SUM(venb.\"RentalFee\"), 0) AS TotalAmount " +
                "FROM software2024.customer c " +
                "LEFT JOIN software2024.\"Events\" e ON c.\"CID\" = e.\"CID\" " +
                "LEFT JOIN software2024.\"VendorBooking\" vb ON e.\"EventID\" = vb.\"EventID\" " +
                "LEFT JOIN software2024.\"VenueBooking\" venb ON e.\"EventID\" = venb.\"EventID\" " +
                "GROUP BY c.\"CID\"";
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String cid = rs.getString("CID");
                double totalAmount = rs.getDouble("TotalAmount");
                customerTotals.add(new CustomerTotal(cid, totalAmount));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return customerTotals;
    }
    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){logger.log(null," An error occurred while opening a new window:");}
    }
    public static class CustomerTotal {
        private final SimpleStringProperty customerId;
        private final SimpleDoubleProperty totalAmount;
        public CustomerTotal(String customerId, double totalAmount) {
            this.customerId = new SimpleStringProperty(customerId);
            this.totalAmount = new SimpleDoubleProperty(totalAmount);
        }
        public String getCustomerId() {return customerId.get();}
        public void setCustomerId(String customerId) {this.customerId.set(customerId);}
        public double getTotalAmount() {return totalAmount.get();}
        public void setTotalAmount(double totalAmount) {this.totalAmount.set(totalAmount);}
    }
}
