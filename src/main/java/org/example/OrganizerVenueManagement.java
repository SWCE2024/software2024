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
    private Button AddBotton;
    @FXML
    private Label Capacity;
    @FXML
    private TextField Capacitytxt;
    @FXML
    private Button DeleteBotton;
    @FXML
    private Label Location;
    @FXML
    private TextField Locationtxt;
    @FXML
    private Label Pricing;
    @FXML
    private TextField Pricingtxt;
    @FXML
    private Button SearchBotton;
    @FXML
    private Button UpdateBotton;
    @FXML
    private Label VenueID;
    @FXML
    private TextField VenueIDtxt;
    @FXML
    private Label VenueName;
    @FXML
    private TextField VenueNametxt;
    @FXML
    private Label VenueView;
    String getVenueId="";
    String getVenueName="";
    String getLocation="";
    String getCapacity="";
     String getPricing="";

    public void readValue(){
        getVenueId=VenueIDtxt.getText();
        getVenueName=VenueNametxt.getText();
        getLocation=Locationtxt.getText();
        getCapacity=Capacitytxt.getText();
        getPricing=Pricingtxt.getText();
    }

    public void clear()
    {
        VenueIDtxt.setText("");
        VenueNametxt.setText("");
        Locationtxt.setText("");
        Capacitytxt.setText("");
        Pricingtxt.setText("");
    }
    @FXML
    void addBottonClicked(ActionEvent event) {
        readValue();

        boolean isRegistered = Database.addVenue(getVenueId, getVenueName, getLocation, getCapacity, getPricing );

        if (isRegistered) {
            JOptionPane.showMessageDialog(null, "Added Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
           clear();

        }
        else
            JOptionPane.showMessageDialog(null, "An error occurred .", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    void deleteBottonClicked(ActionEvent event)
    {
        readValue();

        boolean isRegistered = Database.deleteVenue(getVenueId );
        if (isRegistered)
        {
            JOptionPane.showMessageDialog(null, "Deleted Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
           clear();
        }
        else
            JOptionPane.showMessageDialog(null, "An error occurred .", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    void searchBottonClicked(ActionEvent event)
    {
readValue();

        String sql = " SELECT * FROM software2024.\"Venue\" WHERE \"VenueID\" ="+"'"+ getVenueId+"'" ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs= pstmt.executeQuery();
            if (rs.next())
            {
                VenueIDtxt.setText(rs.getString(1));
                VenueNametxt.setText(rs.getString(2));
                Locationtxt.setText(rs.getString(3));
                Capacitytxt.setText(rs.getString(4));
                Pricingtxt.setText(rs.getString(5));
            }
        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred .", "ERROR", JOptionPane.ERROR_MESSAGE);
            logger.info(e.toString());
        }
    }
    @FXML
    void updateBottonClicked(ActionEvent event)
    {
        readValue();


        String sql = " UPDATE   software2024.\"Venue\" SET  \"VenueName\" = '"+getVenueName +"' ,\"Location\"= '"+Location+"'  ,   \"Capacity\" ='"+Capacity+"'  , \"Pricing\" ='"+Pricing+"'  WHERE \"VenueID\" ='"+getVenueId+"'" ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.executeUpdate();

             clear();
            JOptionPane.showMessageDialog(null, "Updated Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred .", "ERROR", JOptionPane.ERROR_MESSAGE);
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
