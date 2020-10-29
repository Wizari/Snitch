package com.gmail.wizaripost.snitch.mail;

import javax.mail.Authenticator;

public interface IAuthenticatorProvider {

    Authenticator getAuthenticator();
}
