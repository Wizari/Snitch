package com.gmail.wizaripost.snitch.mail;

import javax.mail.MessagingException;
import java.util.ArrayList;

public interface IMailApi {

    void sendMail(String recipient, ArrayList<String> CCRecipient, ArrayList<String> BCCRecipient, String subject, String content) throws MessagingException;

}
