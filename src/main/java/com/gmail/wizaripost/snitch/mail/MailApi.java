package com.gmail.wizaripost.snitch.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailApi implements IMailApi {

    private final ISenderProvider senderProvider;
    private final IPropertiesProvider propertiesProvider;
    private final IAuthenticatorProvider authenticatorProvider;

    public MailApi(ISenderProvider senderProvider,
                   IPropertiesProvider propertiesProvider,
                   IAuthenticatorProvider authenticatorProvider) {
        this.senderProvider = senderProvider;
        this.propertiesProvider = propertiesProvider;
        this.authenticatorProvider = authenticatorProvider;
    }

    @Override
    public void sendMail(String recipient, String subject, String content) throws MessagingException {
        Session session = Session.getInstance(propertiesProvider.getProperties(), authenticatorProvider.getAuthenticator());
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.senderProvider.getSender()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=utf-8");
//        InternetAddress internetAddress = new InternetAddress("");
//        message.addRecipient(Message.RecipientType.CC, internetAddress);
        Transport.send(message);
    }

}
