package com.gmail.wizaripost.snitch.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthenticatorProviderFromMemory implements IAuthenticatorProvider {
    private String password;
    private String myMail;

    public AuthenticatorProviderFromMemory(String myMail, String passwordMyMail) {
        this.password = passwordMyMail;
        this.myMail = myMail;
    }

    @Override
    public Authenticator getAuthenticator() {
            if (password.equals("")) {
                System.out.println("Введите пароль своей почты");
        }

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myMail, password);
            }
        };
        return auth;
    }
}
