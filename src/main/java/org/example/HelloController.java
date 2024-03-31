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

import java.io.IOException;
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
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_CUSTOMER = "customer";
    private static final String ROLE_ORGANIZER = "organizer";

    public  static String getEmail() {
        return emailreturn;
    }

    @FXML
    public void login1Clicked(ActionEvent event) {
        String email = gmailLogIn.getText();
        String password = passwordLogIn.getText();
        try {
            if (Database.validateLogin(email, password, ROLE_ADMIN)) {
                transitionToScene("/org.example/MenuAdmin.fxml");
                logger.log(Level.SEVERE, "login admin successfully");
            } else if (Database.validateLogin(email, password, ROLE_CUSTOMER)) {
                int id = getUserId(email, ROLE_CUSTOMER, "CID", ROLE_CUSTOMER);
                Database.setUserID(String.valueOf(id));
                transitionToScene("/org.example/MenuParticipants.fxml");
                logger.log(Level.SEVERE, "login customer successfully.");
            } else if (Database.validateLogin(email, password, ROLE_ORGANIZER)) {
                int id = getUserId(email, ROLE_ORGANIZER, "OID", ROLE_ORGANIZER);
                Database.setUserID(String.valueOf(id));
                transitionToScene("/org.example/MenuOrganizer.fxml");
                logger.log(Level.SEVERE, "login organizer successfully.");
            } else {
                logger.log(Level.SEVERE, "please signup");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_OPENING_WINDOW, e);
        }
    }


    private void transitionToScene(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) login1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        new FadeIn(root).play();
    }

    private int getUserId(String email, String tableName, String columnId, String logMessage) {
        String sql = String.format("SELECT \"%s\" FROM software2024.\"%s\" WHERE \"GMAIL\" = ?", columnId, tableName);
        int id = 0;
        try (
                Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(columnId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        return id;
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
