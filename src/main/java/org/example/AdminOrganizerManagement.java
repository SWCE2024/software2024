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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.Database.connect;
import static org.example.SignUpController.logger;

public class AdminOrganizerManagement {

    @FXML
    private Button AddBotton;

    @FXML
    private Label Capacity;

    @FXML
    private Button DeleteBotton;

    @FXML
    private Label Location;

    @FXML
    private Label Pricing;

    @FXML
    private Label Pricing1;

    @FXML
    private Button SearchBotton;

    @FXML
    private Button UpdateBotton;

    @FXML
    private Label VenueID;

    @FXML
    private Label VenueName;

    @FXML
    private Label VenueView;

    @FXML
    private TextField address;

    @FXML
    private Label back;

    @FXML
    private TextField gmail;

    @FXML
    private TextField oid;

    @FXML
    private TextField orgname;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;


    String error="An error occurred .";
    String typeError="ERROR";
    @FXML
    void addBottonClicked(ActionEvent event)
    {
        String name=orgname.getText();
        String id=oid.getText();
        String gmailOrg=gmail.getText();
        String phoneOrg=phone.getText();
        String addressOrg=address.getText();
        String passOrg=password.getText();


        boolean isRegistered = Database.addOrg(id, name, addressOrg, gmailOrg, phoneOrg,passOrg );

        if (isRegistered) {
            JOptionPane.showMessageDialog(null, "Added Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            orgname.setText("");
            oid.setText("");
            gmail.setText("");
            phone.setText("");
            address.setText("");
            password.setText("");


        }
        else
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
    }



    @FXML
    void DeleteBottonClicked(ActionEvent event)
    {

            String iD=oid.getText();
            boolean isRegistered = Database.deleteOrg(iD );
            if (isRegistered)
            {
                JOptionPane.showMessageDialog(null, "Deleted Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                orgname.setText("");
                oid.setText("");
                gmail.setText("");
                phone.setText("");
                address.setText("");
                password.setText("");


            }
            else
                JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);


    }

    @FXML
    void searchBottonClicked(ActionEvent event)
    {


        String iD=oid.getText();
        String sql = " SELECT * FROM software2024.\"organizer\" WHERE \"OID\" ="+"'"+ iD+"'" ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs= pstmt.executeQuery();
            if (rs.next())
            {

                orgname.setText(rs.getString(5));
                oid.setText(rs.getString(1));
                gmail.setText(rs.getString(4));
                phone.setText(rs.getString(2));
                address.setText(rs.getString(3));
                password.setText(rs.getString(6));

            }
        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }



    }




    @FXML
    void updateBottonClicked(ActionEvent event)
    {
        String name=orgname.getText();
        String id=oid.getText();
        String gmailOrg=gmail.getText();
        String phoneOrg=phone.getText();
        String addressOrg=address.getText();
        String passOrg=password.getText();

        String sql = " UPDATE   software2024.\"organizer\" SET  \"PASSWORD\" = '"+passOrg +"' ,\"PHONENUMBER\"= '"+phoneOrg+"'  ,   \"ADDRESS\" ='"+addressOrg+"'  , \"GMAIL\" ='"+gmailOrg+"'     , \"USERNAME\" ='"+name+"'    WHERE \"VenueID\" ='"+id+"'" ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            orgname.setText("");
            oid.setText("");
            gmail.setText("");
            phone.setText("");
            address.setText("");
            password.setText("");

            JOptionPane.showMessageDialog(null, "Updated Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


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
        }catch (IOException e){
            logger.info(error+" while opening a new window:");
        }

    }

}
