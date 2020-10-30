package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.entity.Employee;
import com.gmail.wizaripost.snitch.repository.EmployeeDataStorage;

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
            JAXBContext context = JAXBContext.newInstance(EmployeeDataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EmployeeDataStorage result = (EmployeeDataStorage) unmarshaller.unmarshal(file);
            return result.getEmployeeDescriptors();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getEmployees(String groupId) {
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeDataStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EmployeeDataStorage result = (EmployeeDataStorage) unmarshaller.unmarshal(file);
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
