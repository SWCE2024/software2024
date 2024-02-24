package org.example;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class HelloController {

    @FXML
    private TextField gmailLogIn;

    @FXML
    private Button login1;

    @FXML
    private PasswordField passwordLogIn;

    @FXML
    private Button signUp;

    @FXML
    private Button start;

    @FXML
    void login1Clicked(ActionEvent event) {
        try {
            int flag = 0;
            String query = "SELECT GMAIL, PASSWORD FROM ADMIN WHERE GMAIL = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, gmailLogIn.getText()); // use the text field input directly
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String gmail = rs.getString("GMAIL");
                String password = rs.getString("PASSWORD");
                Parent root;
                if (!passwordLogIn.getText().equals(password)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    break;
                } else {
                    FXMLLoader fxmlLoader;
                    root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
                    flag = 1;
                    Stage stage = (Stage) login1.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                    new FadeIn(root).play();
                    break;
                }
            }
            if (flag == 0) JOptionPane.showMessageDialog(null, "Incorrect Gmail !");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the next scene");
        }
    }

    @FXML
    void signUp1Clicked(ActionEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/SignUp.fxml"));
            Stage stage = (Stage) login1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void startClicked(MouseEvent mouseEvent) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("/org.example/login.fxml"));
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
