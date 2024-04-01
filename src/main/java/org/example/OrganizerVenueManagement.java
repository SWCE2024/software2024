package org.example;
        import animatefx.animation.FadeIn;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

        import javax.swing.*;
        import java.io.IOException;
        import java.sql.*;
        import static org.example.Database.connect;
        import static org.example.SignUpController.logger;

public class OrganizerVenueManagement {
    @FXML
    private Label back;
    @FXML
    private Button addBotton;
    @FXML
    private Label capacity;
    @FXML
    private TextField capacityTxt;
    @FXML
    private Button deleteBotton;
    @FXML
    private Label location;
    @FXML
    private TextField locationTxt;
    @FXML
    private Label pricing;
    @FXML
    private TextField pricingTxt;
    @FXML
    private Button searchBotton;
    @FXML
    private Button updateBotton;
    @FXML
    private Label venueId;
    @FXML
    private TextField venueIdtxt;
    @FXML
    private Label venueName;
    @FXML
    private TextField venueNametxt;
    @FXML
    private Label venueView;
    String getVenueId="";
    String getVenueName="";
    String getLocation="";
    String getCapacity="";
     String getPricing="";
     String error="ERROR";
     String info ="INFO";
     String anError ="An error occurred .";

    public void readValue(){
        getVenueId=venueIdtxt.getText();
        getVenueName=venueNametxt.getText();
        getLocation=locationTxt.getText();
        getCapacity=capacityTxt.getText();
        getPricing=pricingTxt.getText();
    }

    public void clear()
    {
        venueIdtxt.setText("");
        venueNametxt.setText("");
        locationTxt.setText("");
        capacityTxt.setText("");
        pricingTxt.setText("");
    }
    @FXML
    void addBottonClicked(ActionEvent event) {
        readValue();

        boolean isRegistered = Database.addVenue(getVenueId, getVenueName, getLocation, getCapacity, getPricing );

        if (isRegistered) {
            JOptionPane.showMessageDialog(null, "Added Successfully.", info, JOptionPane.INFORMATION_MESSAGE);
           clear();

        }
        else
            JOptionPane.showMessageDialog(null, anError, error, JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    void deleteBottonClicked(ActionEvent event)
    {
        readValue();

        boolean isRegistered = Database.deleteVenue(getVenueId );
        if (isRegistered)
        {
            JOptionPane.showMessageDialog(null, "Deleted Successfully.", info, JOptionPane.INFORMATION_MESSAGE);
           clear();
        }
        else
            JOptionPane.showMessageDialog(null, anError, error, JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    void searchBottonClicked(ActionEvent event)
    {
readValue();

        String sql = " SELECT \"VenueName\" ,\"Location\" ,\"Capacity\" ,\"Pricing\" FROM software2024.\"Venue\" WHERE \"VenueID\" =?" ;
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1,getVenueId);
                ResultSet rs= pstmt.executeQuery();
                if (rs.next())
                {
                    venueIdtxt.setText(rs.getString(1));
                    venueNametxt.setText(rs.getString(2));
                    locationTxt.setText(rs.getString(3));
                    capacityTxt.setText(rs.getString(4));
                    pricingTxt.setText(rs.getString(5));
                }
            }

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, anError, error, JOptionPane.ERROR_MESSAGE);
            logger.info(e.toString());
        }
    }
    @FXML
    void updateBottonClicked(ActionEvent event)
    {
        readValue();

        String sql = " UPDATE   software2024.\"Venue\" SET  \"VenueName\" = ? ,\"Location\"= ? ,   \"Capacity\" = ? , \"Pricing\" = ? WHERE \"VenueID\" = ? " ;
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1,getVenueName);
                pstmt.setString(2, getLocation);
                pstmt.setString(3,getCapacity);
                pstmt.setString(4,getPricing);
                pstmt.setString(5,getVenueId);


                 pstmt.executeUpdate();

                 clear();
                JOptionPane.showMessageDialog(null, "Updated Successfully.", info, JOptionPane.INFORMATION_MESSAGE);

            }

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, anError, error, JOptionPane.ERROR_MESSAGE);
            logger.info(e.toString());
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
