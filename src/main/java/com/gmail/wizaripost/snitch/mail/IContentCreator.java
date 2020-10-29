package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.logic.Employee;

import java.util.List;

public interface IContentCreator {
    String createContent(List<Employee> employeeList);
}
