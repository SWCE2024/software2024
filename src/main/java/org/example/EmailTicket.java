package org.example;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.MessagingException;
import java.util.logging.Logger;
public class EmailTicket
{
    private EmailTicket(){
        throw new IllegalStateException(" EmailTicket class");
    }

    public static void sendEmail(String recipientEmailcustumer, String subject, String messageText) throws MessagingException
    {
        Logger logger = Logger.getLogger(EmailTicket.class.getName());


        String senderEmail = Password.getUser();
        String senderPassword = Password.getPassword();

        // Recipient's email address
        String recipientEmail = recipientEmailcustumer;

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
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Send message
            Transport.send(message);
            logger.info("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }





    }


}
