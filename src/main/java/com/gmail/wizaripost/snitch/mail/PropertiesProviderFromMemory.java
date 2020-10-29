package com.gmail.wizaripost.snitch.mail;

import java.util.Properties;

public class PropertiesProviderFromMemory implements IPropertiesProvider {
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }
}
