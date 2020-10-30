package com.gmail.wizaripost.snitch.mail;

public class SenderProviderFromMemory implements ISenderProvider {

    private String myMail;

    public SenderProviderFromMemory(String myMail) {
        this.myMail = myMail;
    }

    // TODO: 30.10.2020  заменить почту
    @Override
    public String getSender() {
        return myMail;
    }
}
