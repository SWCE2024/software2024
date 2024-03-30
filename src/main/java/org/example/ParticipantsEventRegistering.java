package org.example;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ParticipantsEventRegistering {

    private String temp;

    @FXML
    private Button addEvent;

    @FXML
    private Button addImage;

    @FXML
    private Label back;
    @FXML
    private TextField eventtype;

    @FXML
    private TextField attendeCount;

    @FXML
    private TextField eventDate;

    @FXML
    private TextField eventName;

    @FXML
    private TextField eventTime;

    @FXML
    private Label next;

    @FXML
    private ImageView image;

    @FXML
    private ComboBox<String> ComboBox;

  public void initialize() {
      // Initialize the ComboBox with items
      ObservableList<String> items = FXCollections.observableArrayList("Choose...","Garden", "Hall", "Beach", "Office","Stadium","Conference");
      ComboBox.setItems(items);

      // Set an initial selection if needed
      ComboBox.getSelectionModel().select(0);
  }


    @FXML
    void backClicked(MouseEvent event) {
      try {
          Parent root;
          root = FXMLLoader.load(getClass().getResource("/org.example/MenuParticipants.fxml"));
          Stage stage=(Stage) back.getScene().getWindow();
          stage.setScene(new Scene(root));
          stage.show();
          FadeIn fadeIn = new FadeIn(root);
          fadeIn.play();
          
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

    }

    @FXML
    void nextClicked(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/ParticipantsVendor.fxml"));
            Stage stage=(Stage) next.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            FadeIn fadeIn = new FadeIn(root);
            fadeIn.play();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void addNewEvent(ActionEvent event) throws SQLException {
      if (!TESTINPUT.dateTest(eventDate.getText()))
          JOptionPane.showMessageDialog(null,"Unvalid Date","ERROR",JOptionPane.ERROR_MESSAGE);
      else if (!TESTINPUT.timeTest(eventTime.getText()))
          JOptionPane.showMessageDialog(null,"Unvalid Time","ERROR",JOptionPane.ERROR_MESSAGE);
      else if (!TESTINPUT.countTest(attendeCount.getText()))
          JOptionPane.showMessageDialog(null,"Unvalid Attende Count","ERROR",JOptionPane.ERROR_MESSAGE);
      else {
          Connection con = Database.connect();
          PreparedStatement preparedStatement=null;

          try {

              String name = eventName.getText();
              String date = eventDate.getText();
              String time = eventTime.getText()+":00";
              String count = attendeCount.getText();
              String type = eventtype.getText();
              String comboBoxValue = (String) getSelectedComboBoxItem(ComboBox);
              String imagePath = getImagePathFromImageView(image);

              String dateString = date; // Replace this with your actual date string
              java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);

              String timeString = time;  // Replace this with your actual time string
              java.sql.Time sqlTime = java.sql.Time.valueOf(timeString);

              String sql = "INSERT INTO software2024.\"Events\" (\"CID\",\"EventType\",\"EventName\",\"EventDate\",\"Location\",\"AttendeeCount\",\"MediaURL\",\"EventTime\") VALUES (?,?,?,?,?,?,?,?)";

              preparedStatement = con.prepareStatement(sql);


              preparedStatement.setString(1, Database.getUserID());  // Assuming CID is an integer
              preparedStatement.setString(2, type);
              preparedStatement.setString(3, name);
              preparedStatement.setDate(4, sqlDate);
              preparedStatement.setString(5, comboBoxValue);
              preparedStatement.setString(6, count);
              preparedStatement.setString(7, imagePath);
              preparedStatement.setTime(8, sqlTime);

               preparedStatement.executeUpdate();
              JOptionPane.showMessageDialog(null, "Done", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);

          } catch (SQLException e) {
              e.printStackTrace();
              // Handle SQL exceptions or display an error message if needed
              JOptionPane.showMessageDialog(null, "Error", "Failed to add event", JOptionPane.ERROR_MESSAGE);
          }finally {
              if (preparedStatement != null) {
                  try {
                      preparedStatement.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                      // Handle exception if closing the statement fails
                  }
              }
          }

      }

    }

    private String getImagePathFromImageView(ImageView imageView) {
        // Assuming you set the image using setImage(new Image("file:/path/to/image.jpg"))
        // If you have a different method for setting the image, adjust accordingly
        Image image = imageView.getImage();
        String imagePath;
        imagePath = image.getUrl().substring("file:".length());
        return imagePath;
    }
    private String getSelectedComboBoxItem(ComboBox<String> comboBox) {
        // Assuming the ComboBox contains strings
        return comboBox.getSelectionModel().getSelectedItem();
    }




    @FXML
    void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage =(Stage) addImage.getScene().getWindow();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","*.png","*.jpg","*.gif"));
        File file=fileChooser.showOpenDialog(stage);
        if (file != null){
            String iconimage= file.getAbsolutePath();
            System.out.println(iconimage);
            image.setImage(new Image(iconimage));
            temp=iconimage;

        }
        else if (!TESTINPUT.pictureTest(temp))
            JOptionPane.showMessageDialog(null,"Unvalid Picture","ERROR",JOptionPane.ERROR_MESSAGE);


    }


}
