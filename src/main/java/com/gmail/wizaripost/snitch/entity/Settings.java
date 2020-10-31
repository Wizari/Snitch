package com.gmail.wizaripost.snitch.entity;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "settings")
public class Settings {
    private String key;
    private String values;

    public Settings() {
    }

    public Settings(String key, String values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
