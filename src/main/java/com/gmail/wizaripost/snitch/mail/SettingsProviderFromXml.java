package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.entity.Settings;
import com.gmail.wizaripost.snitch.repository.SettingsDataStorage;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class SettingsProviderFromXml implements ISettingsProvider {

    private File file;

    public SettingsProviderFromXml(File file) {
        this.file = file;
    }

    @Override
    public List<Settings> getSettings() {
        try {
            JAXBContext context = JAXBContext.newInstance(SettingsDataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SettingsDataStorage result = (SettingsDataStorage) unmarshaller.unmarshal(file);
            return result.getSettingsDescriptors();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSettings(String key) {
        try {
            JAXBContext context = JAXBContext.newInstance(SettingsDataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SettingsDataStorage result = (SettingsDataStorage) unmarshaller.unmarshal(file);
            List<Settings> settings = result.getSettingsDescriptors();
            for (int i = 0; i < settings.size(); i++) {
                if (settings.get(i).getKey().equals(key)) {
                    return settings.get(i).getValues();
                }
            }
            return null;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
 @Override
    public void saveSettingsToFile(File file, List<Settings> settings) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(SettingsDataStorage.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Обёртываем наши данные об адресатах.
            SettingsDataStorage wrapper = new SettingsDataStorage();
            wrapper.setSettingsDescriptors(settings);

            // Маршаллируем и сохраняем XML в файл.
            marshaller.marshal(wrapper, file);

            // Сохраняем путь к файлу в реестре.
//            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
}
