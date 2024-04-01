package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.SignUpController.logger;
public class ReminderCustomer
{
    private static final Properties teketProperties = new Properties();

    static {
        try {
            teketProperties.load(new FileInputStream("src/main/java/org/example/password.properties"));
        } catch (IOException e) {
            String errorMsg = "Failed to load email properties from src/main/java/org/example/password.properties";
            logger.log(Level.SEVERE, errorMsg, e);
            throw new IllegalStateException("Failed to load email configurations, check properties path: " + "src/main/java/org/example/password.properties", e);
        }
    }

    private ReminderCustomer(){

    }
    public static void sendReminder(String email)
    {


        final String senderEmail = teketProperties.getProperty("teket.username");

        final String senderPassword = teketProperties.getProperty("teket.password");


        // Recipient's email address
        String recipientEmail = email;

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("REMINDER OF TODAY IS CEREMONY");
            message.setText("We would like to remind you that today is your party. We thank you for using our application and we wish you a happy party!");

            Transport.send(message);
            Logger logger = Logger.getLogger(ReminderCustomer.class.getName());
            logger.info("Email sent successfully.");

        } catch (MessagingException e) {
            logger.info(e.toString());
        }

    }




}
