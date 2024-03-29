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
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static org.example.SignUpController.logger;
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

    private static String emailreturn;

    private static final String ERROR_OPENING_WINDOW = "An error occurred while opening a new window:";


    public  static String getEmail()
    {
        return emailreturn;
    }

    @FXML
    public void login1Clicked(ActionEvent event) {
        String email = gmailLogIn.getText();
        String password = passwordLogIn.getText();
        try {

            if (Database.validateLogin(email, password, "ADMIN")) {
                logger.log(Level.SEVERE, "login admin successfully");
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuAdmin.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
            } else if (Database.validateLogin(email, password, "customer")) {
                logger.log(Level.SEVERE, "login customer successfully.");
                Parent root;
                emailreturn=email;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuParticipants.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
                String sql = "SELECT \"CID\" FROM software2024.\"customer\" WHERE \"GMAIL\" = ?";
                int id = 0;

                try (
                        Connection connection=Database.connect();
                        PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
                    preparedStatement.setString(1,email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                         id = resultSet.getInt("CID");
                    }
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
                Database.setUserID(String.valueOf(id));
            }
            else if (Database.validateLogin(email, password, "organizer")) {
                logger.log(Level.SEVERE, "login organizer successfully.");
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/org.example/MenuOrganizer.fxml"));
                Stage stage = (Stage) login1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                new FadeIn(root).play();
            } else {
                logger.log(Level.SEVERE, "please signup");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    @FXML
    void signUp1Clicked(ActionEvent event) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/SignUp.fxml"));
            Stage stage = (Stage) signUp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
    public void startClicked( ) {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/org.example/login.fxml"));
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }
}
