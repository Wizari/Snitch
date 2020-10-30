package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.entity.Employee;

import java.util.List;

public interface IEmployeeProvider {

    List<Employee> getEmployees();

    List<Employee> getEmployees(String groupId);
}
