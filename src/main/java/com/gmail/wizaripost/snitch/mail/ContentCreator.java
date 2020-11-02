package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.entity.Employee;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContentCreator implements IContentCreator {
    private StringBuilder sb = new StringBuilder();
    private String emailBody;
    private String emailBottom;

    public ContentCreator(String emailBody, String emailBottom) {
        this.emailBody = emailBody;
        this.emailBottom = emailBottom;
    }

    @Override
    public String createContent(List<Employee> employeeList) {
        sb.setLength(0);
        sb
                .append(emailBody)
                .append("</font>")
                .append("</font>")
                .append("</font>")
                .append("</font>")
                .append("</font>");
        String content = sb.toString();
        content = content.replaceAll("@EmployeesList", getEmployeesList(employeeList));
        content = content.replaceAll("@GetDate", getDate());
        return content;
    }

    private StringBuilder getEmployee(Employee employee) {
        StringBuilder builder = new StringBuilder();
        if (employee.getStatus()) {
            builder
                    .append("<li>")
                    .append(employee.getName())
                    .append("\t\t\t")
                    .append("</li>")
                    .append("\n");
        }
        return builder;
    }


    private String getEmployeesList(List<Employee> employeeList) {
        StringBuilder builder = new StringBuilder();
        builder
                .append("<font color=\"black\">")
                .append("<ul>")
                .append("\n");

        for (Employee employee : employeeList) {
            builder.append(getEmployee(employee));
        }
        builder.append("</ul>");
        return builder.toString();
    }

    private String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM YYYY", myDateFormatSymbols);
        String stringDate = new String("<p>" + dateFormat.format(currentDate) + "</p>" + "\n");
//        return dateFormat.format(currentDate);
        return stringDate;
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}

