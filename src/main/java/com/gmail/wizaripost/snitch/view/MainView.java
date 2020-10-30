package com.gmail.wizaripost.snitch.view;

import com.gmail.wizaripost.snitch.entity.Employee;
import com.gmail.wizaripost.snitch.mail.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

import javax.mail.MessagingException;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainView implements Initializable {

    @FXML
    public CheckBox checkBox1;
    @FXML
    public VBox checkBoxContainer;

    private IMailApi mailApi;
    private IContentCreator contentCreator;
    private IEmployeeProvider employeeProvider;
    @Getter
    public ISettingsProvider settingsProvider;
    private List<Employee> employees;

//    public ISettingsProvider getSettingsProvider() {
//        return settingsProvider;
//    }

    @FXML
    private TextField groupId;

    private String textGroupId = "0";

//    @FXML
//    void groupIdButton(ActionEvent groupIdButton) {
//        this.employees = this.employeeProvider.getEmployees(this.groupId.getText());
//        updateEmployees();
//    }

    public MainView() {
        this.settingsProvider = new SettingsProviderFromXml(new File("settings.xml"));
        IAuthenticatorProvider authenticatorProvider = new AuthenticatorProviderFromMemory(
                settingsProvider.getSettings("myMail"),
                settingsProvider.getSettings("passwordMyMail"));
        IPropertiesProvider propertiesProvider = new PropertiesProviderFromMemory(
                settingsProvider.getSettings("mail.smtp.host"),
                settingsProvider.getSettings("mail.smtp.port"),
                settingsProvider.getSettings("mail.smtp.auth"),
                settingsProvider.getSettings("mail.smtp.starttls.enable")
        );
        ISenderProvider senderProvider = new SenderProviderFromMemory(settingsProvider.getSettings("myMail"));
        this.mailApi = new MailApi(senderProvider, propertiesProvider, authenticatorProvider);
        this.contentCreator = new ContentCreator(
                settingsProvider.getSettings("emailBody"),
                settingsProvider.getSettings("emailBottom")
        );
        this.employeeProvider = new EmployeeProviderFromXml(new File("employees.xml"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.employees = this.employeeProvider.getEmployees();
        updateEmployees();
    }


    private void updateEmployees() {
        this.checkBoxContainer.getChildren().clear();
        for (Employee employee : this.employees) {
            CheckBox checkBox = new CheckBox(employee.getName());
            checkBox.setSelected(employee.getStatus());
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                employee.setStatus(newValue);
            });
            this.checkBoxContainer.getChildren().add(checkBox);
        }
    }

    public void send(ActionEvent actionEvent) {
        String content = this.contentCreator.createContent(employees);
        try {
            this.mailApi.sendMail(
                    settingsProvider.getSettings("targetMail"),
                    settingsProvider.getSettings("emailHeader"),
                    content);
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
}
