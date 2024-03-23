package org.example;
import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;
import static org.example.SignUpController.logger;

public class calender {

    @FXML
    private Label back;

    @FXML
    private Button findsearch;

    @FXML
    private DatePicker dateinter;

    @FXML
    private TableColumn<EventCalender, String> eventDate;

    @FXML
    private TableColumn<EventCalender, String> eventName;

    @FXML
    private TableColumn<EventCalender, String> eventTime;

    @FXML
    private TableColumn<EventCalender, String> eventType;

    @FXML
    private TableView<EventCalender> table;




    public void initialize() {
        eventDate.setCellValueFactory(cellData -> cellData.getValue().eventDateProperty());
        eventName.setCellValueFactory(cellData -> cellData.getValue().EventNameProperty());
        eventTime.setCellValueFactory(cellData -> cellData.getValue().eventTimeProperty());
        eventType.setCellValueFactory(cellData -> cellData.getValue().eventTypeProperty());
        loadDate();
    }



    private void loadDate() {
        String sql =
                "SELECT * FROM software2024.\"Events\"  " ;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            ResultSet rs= pstmt.executeQuery();
            table.getItems().clear();
            while (rs.next()) {
                String eventName = rs.getString("EventName");
                String eventDate = rs.getString("EventDate");
                String eventType = rs.getString("EventType");
                String eventTime = rs.getString("EventTime");

                EventCalender eventCalender = new EventCalender(eventName, eventDate, eventTime, eventType);
                table.getItems().add(eventCalender);
            }
        } catch (SQLException e) {
         //   logger.log(null,"An error occurred while opening a new window:");
            e.printStackTrace();
        }
    }





    @FXML
    void backClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/MenuParticipants.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(null," An error occurred while opening a new window:");
        }
    }




    @FXML
    void searchClicked(MouseEvent event)
    {

        if (!dateinter.getValue().toString().isEmpty() )
        {
            table.getItems().clear();
            loadSpecifiedDate(dateinter.getValue().toString());
        } else {
            loadDate();
        }


    }

    private void loadSpecifiedDate(String dateOfSearch)
    {
        String sql =
                "SELECT * FROM software2024.\"Events\"  WHERE \"EventDate\" ="+"'"+ dateOfSearch+"'"  ;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            ResultSet rs= pstmt.executeQuery();
            table.getItems().clear();
            while (rs.next()) {
                String eventName = rs.getString("EventName");
                String eventDate = rs.getString("EventDate");
                String eventType = rs.getString("EventType");
                String eventTime = rs.getString("EventTime");

                EventCalender eventCalender = new EventCalender(eventName, eventDate, eventTime, eventType);
                table.getItems().add(eventCalender);
            }
        } catch (SQLException e) {
            //   logger.log(null,"An error occurred while opening a new window:");
            e.printStackTrace();
        }






    }
}
