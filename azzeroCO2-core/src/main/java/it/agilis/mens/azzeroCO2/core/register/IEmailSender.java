package it.agilis.mens.azzeroCO2.core.register;


import it.agilis.mens.azzeroCO2.core.register.impl.Email;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/30/11
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IEmailSender {
    void sendMail(Email email) throws MessagingException, IOException;
}
