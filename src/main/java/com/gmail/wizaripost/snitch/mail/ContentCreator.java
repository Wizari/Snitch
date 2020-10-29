package com.gmail.wizaripost.snitch.mail;

import com.gmail.wizaripost.snitch.logic.Employee;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ContentCreator implements IContentCreator {
    StringBuilder sb = new StringBuilder();
    int enumerator = 0;

    @Override
    public String createContent(List<Employee> employeeList) {
//        StringBuilder sb = new StringBuilder();
        sb
//                .append("<font color=\"black\">")
                .append("<font color=\"gray\">")
                .append("<p>")
                .append("Доброе утро, Лариса!")
                .append("</p>")
                .append("<p>")
                .append(getDate() + "")
                .append("</p>")
                .append("\n")
                .append("</font>")
                .append("<font color=\"black\">")
                .append("<ul>")
                .append("\n")
        ;
        for (Employee employee : employeeList) {
            getEmployee(employee);
        }
        getFinale();
        return sb.toString();
    }

    private void getFinale() {
        String finalMessage = new String();
        if (enumerator == 1) {
            finalMessage = "К работе удаленно приступил.\n";
        } else {
            finalMessage = "К работе удаленно приступили.\n";
        }
        sb
                .append("</ul>")
                .append("<font color=\"gray\">")
                .append("<p>")
                .append(finalMessage)
                .append("</p>")
                .append("</font>")
                .append("</font>");
        enumerator = 0;
    }

    private void getEmployee(Employee employee) {
        if (employee.getStatus()) {
            enumerator++;
            sb
                    .append("<li>")
                    .append(employee.getName())
                    .append("\t\t\t")
                    .append("</li>")
                    .append("\n");
        }
    }

    private String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM YYYY", myDateFormatSymbols);
        return dateFormat.format(currentDate);
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}

