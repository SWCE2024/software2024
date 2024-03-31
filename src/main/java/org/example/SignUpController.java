package org.example;
import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController {
    public static final Logger logger = Logger.getLogger(SignUpController.class.getName());

    @FXML
    private TextField address;
    @FXML
    private TextField userName;
    @FXML
    private TextField gmail;
    @FXML
    private TextField id;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private Label back;
    @FXML
    private Button signUp2;
    private static final String ERROR_MESSAGE = "ERROR";

    @FXML
    void backClicked(MouseEvent event) {
        loadScene("/org.example/hello-view.fxml", back);
    }

    @FXML
    void signUp2Clicked(ActionEvent event) {
        FormInput input = new FormInput(id.getText(), phoneNumber.getText(), address.getText(), gmail.getText(), userName.getText(), password.getText());

        if (input.isValid()) {
            boolean isRegistered = Database.registerCustomer(input.getId(), input.getPhoneNumber(), input.getAddress(), input.getGmail(), input.getUserName(), input.getPassword());
            if (isRegistered) {
                loadScene("/org.example/MenuParticipants.fxml", signUp2);
            } else {
                showMessageDialog("Registration failed. Please check the information provided and try again.");
            }
        } else {
            showMessageDialog("Please enter valid information in all fields.");
        }
    }

    private void loadScene(String fxmlPath, javafx.scene.Node sceneNode) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) sceneNode.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
    }

    private static class FormInput {
        private final String id;
        private final String phoneNumber;
        private final String address;
        private final String gmail;
        private final String userName;
        private final String password;


        public FormInput(String id, String phoneNumber, String address, String gmail, String userName, String password) {
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.gmail = gmail;
            this.userName = userName;
            this.password = password;
        }

        boolean isValid() {
            return TESTINPUT.idTest(id) && TESTINPUT.phoneNumberTest(phoneNumber) && TESTINPUT.gmailTest(gmail) && TESTINPUT.passwordTest(password);
        }

        public String getId() { return id; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getAddress() { return address; }
        public String getGmail() { return gmail; }
        public String getUserName() { return userName; }
        public String getPassword() { return password; }
    }
}
