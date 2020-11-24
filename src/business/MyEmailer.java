package business;

import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;

public class MyEmailer {
    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
    private static final String SMTP_AUTH_USER = "trabalhos.ftt.2018.1@gmail.com";
    private static final String SMTP_AUTH_PWD = "Trabalhos@30056TT";

    public void SendMail(String text, String content, String recipient, String subject) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");

        /*Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(properties, auth);

        MimeMessage message = new MimeMessage(mailSession);
        Multipart multipart = new MimeMultipart("alternative");
        BodyPart part1 = new MimeBodyPart();
        part1.setText(text);
        BodyPart part2 = new MimeBodyPart();
        part2.setContent(content, "text/html");
        multipart.addBodyPart(part1);
        multipart.addBodyPart(part2);
        message.setFrom(new InternetAddress("trabalhos.ftt.2018.1@gmail.com"));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(multipart);

        Transport transport = mailSession.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();*/
    }

   /* private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }*/
}
