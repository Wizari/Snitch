package com.gmail.wizaripost.snitch.mail;

import java.util.Properties;

public class PropertiesProviderFromMemory implements IPropertiesProvider {

    String host;
    String port;
    String auth;
    String starttlsEnable;

    public PropertiesProviderFromMemory(String host, String port, String auth, String starttlsEnable) {
        this.host = host;
        this.port = port;
        this.auth = auth;
        this.starttlsEnable = starttlsEnable;
    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        return properties;
    }
}
