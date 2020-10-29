package com.gmail.wizaripost.snitch.mail;

import javax.mail.MessagingException;

public interface IMailApi {

    void sendMail(String recipient, String subject, String content) throws MessagingException;

}
