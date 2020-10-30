package com.gmail.wizaripost.snitch.repository;


import com.gmail.wizaripost.snitch.entity.Employee;
import com.gmail.wizaripost.snitch.entity.Settings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDataStorage {

    @XmlElement(name = "descriptor")
    private List<Settings> settingsDescriptors;
}
