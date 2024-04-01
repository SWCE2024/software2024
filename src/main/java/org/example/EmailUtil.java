package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailUtil {

    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());
    private static final Properties emailProperties = new Properties();

    static {
        try {
            emailProperties.load(new FileInputStream("src/main/java/org/example/mail.properties"));
        } catch (IOException e) {
            String errorMsg = "Failed to load email properties from src/main/java/org/example/mail.properties";
            logger.log(Level.SEVERE, errorMsg, e);
            throw new IllegalStateException("Failed to load email configurations, check properties path: " + "src/main/java/org/example/mail.properties", e);
        }
    }



    private EmailUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void sendEmail(String recipientEmail, String subject, String messageText) throws MessagingException {
        final String username = emailProperties.getProperty("email.username");
        final String password = emailProperties.getProperty("email.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fromemail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            logger.info( "Email sent successfully to :"+ recipientEmail);
        } catch (MessagingException e) {
            logger.info("Failed to send email to " + recipientEmail + " with subject: " + subject);
            throw new MessagingException("Email sending to " + recipientEmail + " failed.", e);
        }
    }
}
