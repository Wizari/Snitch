package com.gmail.wizaripost.snitch.mail;

import com.sun.xml.internal.bind.v2.TODO;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthenticatorProviderFromMemory implements IAuthenticatorProvider {
    // TODO: ввести пасс ("" -> "ХХХХ")
    private String pas = "";
    @Override
    public Authenticator getAuthenticator() {
//        if (pas == "") {
            if (pas.equals("")) {
                System.out.println("Введите пароль своей почты");
        }

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // TODO: ввести свою почту ("" -> "XXX@gmail.com")
                return new PasswordAuthentication("", pas);
            }
        };
        return auth;
    }
}
