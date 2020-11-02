package com.gmail.wizaripost.snitch.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
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
    public void sendMail(String recipient,
                         ArrayList<String> CCRecipient,
                         ArrayList<String> BCCRecipient,
                         String subject,
                         String content) throws MessagingException {
        Session session = Session.getInstance(propertiesProvider.getProperties(), authenticatorProvider.getAuthenticator());
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.senderProvider.getSender()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        System.out.println(CCRecipient);
        System.out.println(BCCRecipient);

        InternetAddress[] ccAddress = new InternetAddress[CCRecipient.size()];
            // To get the array of ccaddresses
            for (int i = 0; i < CCRecipient.size(); i++) {
                if (CCRecipient.get(i) != null && !CCRecipient.get(i).isEmpty()) {
                    ccAddress[i] = new InternetAddress(CCRecipient.get(i));
                    message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
                }
            }

            InternetAddress[] bccAddress = new InternetAddress[BCCRecipient.size()];
            // To get the array of bccaddresses
            for (int i = 0; i < BCCRecipient.size(); i++) {
                if (BCCRecipient.get(i) != null && !BCCRecipient.get(i).isEmpty()) {
                    bccAddress[i] = new InternetAddress(BCCRecipient.get(i));
                    message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
                }
            }

        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=utf-8");
        Transport.send(message);
    }

}
