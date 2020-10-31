package com.gmail.wizaripost.snitch.mail;

public class SenderProviderFromMemory implements ISenderProvider {

    private String myMail;

    public SenderProviderFromMemory(String myMail) {
        this.myMail = myMail;
    }

    @Override
    public String getSender() {
        return myMail;
    }
}
