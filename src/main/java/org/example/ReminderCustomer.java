package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

import static org.example.Password.getPassword;
import static org.example.Password.getUser;
import static org.example.SignUpController.logger;

public class ReminderCustomer
{

    private ReminderCustomer(){

    }
    public static void sendReminder(String email)
    {


        String senderEmail = getUser;
        String senderPassword = getPassword;

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
