package it.agilis.mens.azzeroCO2.core.register.impl;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/30/11
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */

import it.agilis.mens.azzeroCO2.core.register.IEmailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender implements IEmailSender {

    private String host = "smtp.gmail.com";
    private String port = "465";
    private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSSL_FACTORY() {
        return SSL_FACTORY;
    }

    public void setSSL_FACTORY(String SSL_FACTORY) {
        this.SSL_FACTORY = SSL_FACTORY;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendMail(Email email) throws MessagingException , IOException {

        String recipients[]= email.getToUser();
        String subject=email.getSubject();
        String message=email.getBody(true);
        String from=email.getFromUser();

        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.starttls.enable","true");
     //   props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                       // return new PasswordAuthentication("giovannilthd@gmail.com", "liberitutti");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);


        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

}