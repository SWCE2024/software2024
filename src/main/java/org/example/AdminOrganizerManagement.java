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

public class AdminOrganizerManagement
{


    @FXML
    private Button addBotton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label ID;

    @FXML
    private Label Location;

    @FXML
    private Label Name;

    @FXML
    private Button searchBotton;

    @FXML
    private Button updateBotton;

    @FXML
    private Label VenueView;

    @FXML
    private TextField address;

    @FXML
    private Label back;

    @FXML
    private Label fone;

    @FXML
    private TextField gmail;

    @FXML
    private Label gmll;

    @FXML
    private TextField oid;

    @FXML
    private TextField orgname;

    @FXML
    private Label pacc;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    String getName="";
    String getId="";
    String gmailOrg="";
    String phoneOrg="";
    String addressOrg="";
    String passOrg="";





    String error="An error occurred .";
    String typeError="ERROR";

    public void clear()
    {
        orgname.setText("");
        oid.setText("");
        gmail.setText("");
        phone.setText("");
        address.setText("");
        password.setText("");
    }

    public void getStrings()
    {
        getName=orgname.getText();
        getId=oid.getText();
         gmailOrg=gmail.getText();
         phoneOrg=phone.getText();
         addressOrg=address.getText();
         passOrg=password.getText();
    }

    @FXML
    void AddBottonClicked(ActionEvent event)
    {
        getStrings();
        boolean isRegistered = Database.addOrg(getId, getName, addressOrg, gmailOrg, phoneOrg,passOrg );


        if (isRegistered)
        {
            JOptionPane.showMessageDialog(null, "Added Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            clear();
        }
        else
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
    }



    @FXML
    void DeleteButtonClicked(ActionEvent event)
    {

            String iD=oid.getText();
            boolean isRegistered = Database.deleteOrg(iD );
            if (isRegistered)
            {
                JOptionPane.showMessageDialog(null, "Deleted Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                clear();
            }

              else
                JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);

    }

    @FXML
    void SearchBottonClicked(ActionEvent event)
    {


        String iD=oid.getText();
        String sql = " SELECT * FROM software2024.\"organizer\" WHERE \"OID\" =?" ;
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1,iD);
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

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
            logger.info(e.toString());
        }
    }


    @FXML
    void UpdateBottonClicked(ActionEvent event)
    {
        getStrings();
        String sql = " UPDATE   software2024.\"organizer\" SET  \"PASSWORD\" = ? ,\"PHONENUMBER\"= ?  ,   \"ADDRESS\" = ?  , \"GMAIL\" = ?    , \"USERNAME\" = ?    WHERE \"OID\" = ?" ;
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql))
            {

                pstmt.setString(1,passOrg);
                pstmt.setString(2,phoneOrg);
                pstmt.setString(3,addressOrg);
                pstmt.setString(4,gmailOrg);
                pstmt.setString(5,getName);
                pstmt.setString(6,getId);


                pstmt.executeUpdate();
                clear();

                JOptionPane.showMessageDialog(null, "Updated Successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, error, typeError, JOptionPane.ERROR_MESSAGE);
            logger.info(e.toString());
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
