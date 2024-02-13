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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    void login1Clicked(ActionEvent event) {
        try {
            int flag=0;
            ResultSet rs = database.createDatabase("select GMAIL,PASSWORD from ADMIN");
            while (rs.next()) {
                String gmail = rs.getString(1);
                String Password = rs.getString(2);
                Parent root;
                if (gmailLogIn.getText().equals(gmail) && !passwordLogIn.getText().equals(Password)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    break;
                } else if (gmailLogIn.getText().equals(gmail) && passwordLogIn.getText().equals(Password)) {

                    FXMLLoader fxmlLoader;
                    root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    flag=1;
                    Stage stage = (Stage) login1.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                    new FadeIn(root).play();
                    break;
                }
            }
            if (flag==0) JOptionPane.showMessageDialog(null, "Incorrect Gmail !");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void signUp1Clicked(ActionEvent event) {
        try{
            Parent root;
            FXMLLoader fxmlLoader;
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage stage = (Stage) login1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void startClicked(MouseEvent mouseEvent) {
    }
}
