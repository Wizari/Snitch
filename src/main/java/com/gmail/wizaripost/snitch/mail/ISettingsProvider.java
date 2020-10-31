package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.entity.Settings;

import java.io.File;
import java.util.List;

public interface ISettingsProvider {

    List<Settings> getSettings();

    String getSettings(String key);

    void saveSettingsToFile(File file, List<Settings> settings);
}
