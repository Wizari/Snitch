package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.logic.Employee;
import com.gmail.wizaripost.snitch.repository.DataStorage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class EmployeeProviderFromXml implements IEmployeeProvider {

    private File file;

    public EmployeeProviderFromXml(File file) {
        this.file = file;
    }

    @Override
    public List<Employee> getEmployees() {
        try {
            JAXBContext context = JAXBContext.newInstance(DataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DataStorage result = (DataStorage) unmarshaller.unmarshal(file);
            return result.getEmployeeDescriptors();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getEmployees(String groupId) {
        try {
            JAXBContext context = JAXBContext.newInstance(DataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DataStorage result = (DataStorage) unmarshaller.unmarshal(file);
            List<Employee> temp = result.getEmployeeDescriptors();
            List<Employee> res = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getGroupId().equals(groupId)) {
                    res.add(temp.get(i));
                }
            }
            return res;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


}
