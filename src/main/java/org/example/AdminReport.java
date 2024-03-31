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
import java.util.logging.Level;
import static org.example.DatabaseUtil.ResultSetProcessor;
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
        String sql = "SELECT c.\"CID\", COALESCE(SUM(vb.\"PaymentAmount\"), 0) + COALESCE(SUM(venb.\"RentalFee\"), 0) AS TotalAmount " +
                "FROM software2024.customer c " +
                "LEFT JOIN software2024.\"Events\" e ON c.\"CID\" = e.\"CID\" " +
                "LEFT JOIN software2024.\"VendorBooking\" vb ON e.\"EventID\" = vb.\"EventID\" " +
                "LEFT JOIN software2024.\"VenueBooking\" venb ON e.\"EventID\" = venb.\"EventID\" " +
                "GROUP BY c.\"CID\"";

        return FXCollections.observableArrayList(DatabaseUtil.executeQuery(sql, rs -> new CustomerTotal(rs.getString("CID"), rs.getDouble("TotalAmount"))));
    }

    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }

    public static class CustomerTotal {
        private final SimpleStringProperty customerId = new SimpleStringProperty();
        private final SimpleDoubleProperty totalAmount = new SimpleDoubleProperty();

        public CustomerTotal(String customerId, double totalAmount) {
            this.customerId.set(customerId);
            this.totalAmount.set(totalAmount);
        }

        public String getCustomerId() { return customerId.get(); }
        public double getTotalAmount() { return totalAmount.get(); }
    }
}
