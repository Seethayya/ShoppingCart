package com.seethayya.shoppingcart.mail;

/**
 * User: Anish
 * Date: 4/26/14
 * Time: 10:46 PM
 */
public interface MailService {

    public void sendMail(String toMailId, String ccMailId, String vmFileLocation, Object... data);

}
