package com.gmail.wizaripost.snitch.view;

import com.gmail.wizaripost.snitch.entity.Employee;
import com.gmail.wizaripost.snitch.entity.Settings;
import com.gmail.wizaripost.snitch.mail.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    public TextArea emailBody;
    public TextField emailHeader;
    public TextArea emailBottom;
    public TextField myMail;
    public TextField targetMail;
    public PasswordField passwordMyMail;
    public AnchorPane settingsPanel;
    public TextField mailSmtpStarttlsEnable;
    public TextField mailSmtpHost;
    public TextField mailSmtpPort;
    public TextField mailSmtpAuth;

    private IMailApi mailApi;
    private IContentCreator contentCreator;
    private IEmployeeProvider employeeProvider;
    @Getter
    public ISettingsProvider settingsProvider;
    private List<Employee> employeesList;
    private List<Settings> settingsList;
    @FXML
    private TextField groupId;
    private String textGroupId = "0";

    //    @FXML
//    void groupIdButton(ActionEvent groupIdButton) {
//        this.employees = this.employeeProvider.getEmployees(this.groupId.getText());
//        updateEmployees();
//    }


    public MainView() {
        installSettings();
        this.employeeProvider = new EmployeeProviderFromXml(new File("employees.xml"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.employeesList = this.employeeProvider.getEmployees();
        this.settingsList = this.settingsProvider.getSettings();
        updateEmployees();
        updateFields();
    }

    private void updateFields() {
        this.emailHeader.setText(settingsProvider.getSettings("emailHeader"));
        this.emailBody.setText(settingsProvider.getSettings("emailBody"));
        this.emailBottom.setText(settingsProvider.getSettings("emailBottom"));
        this.myMail.setText(settingsProvider.getSettings("myMail"));
        this.passwordMyMail.setText(settingsProvider.getSettings("passwordMyMail"));
        this.targetMail.setText(settingsProvider.getSettings("targetMail"));

        this.mailSmtpHost.setText(settingsProvider.getSettings("mail.smtp.host"));
        this.mailSmtpPort.setText(settingsProvider.getSettings("mail.smtp.port"));
        this.mailSmtpAuth.setText(settingsProvider.getSettings("mail.smtp.auth"));
        this.mailSmtpStarttlsEnable.setText(settingsProvider.getSettings("mail.smtp.starttls.enable"));
    }

    private void updateSettingsList() {
        settingsList.clear();
        settingsList.add(new Settings("myMail", myMail.getText()));
        settingsList.add(new Settings("passwordMyMail", passwordMyMail.getText()));
        settingsList.add(new Settings("targetMail", targetMail.getText()));
        settingsList.add(new Settings("emailHeader", emailHeader.getText()));
        settingsList.add(new Settings("emailBody", emailBody.getText()));
        settingsList.add(new Settings("emailBottom", emailBottom.getText()));
        settingsList.add(new Settings("mail.smtp.host", mailSmtpHost.getText()));
        settingsList.add(new Settings("mail.smtp.port", mailSmtpPort.getText()));
        settingsList.add(new Settings("mail.smtp.auth", mailSmtpAuth.getText()));
        settingsList.add(new Settings("mail.smtp.starttls.enable",
                mailSmtpStarttlsEnable.getText()));

    }


    private void updateEmployees() {
        this.checkBoxContainer.getChildren().clear();
        for (Employee employee : this.employeesList) {
            CheckBox checkBox = new CheckBox(employee.getName());
            checkBox.setSelected(employee.getStatus());
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                employee.setStatus(newValue);
            });
            this.checkBoxContainer.getChildren().add(checkBox);
        }
    }

    public void send(ActionEvent actionEvent) {
        System.out.println("____Send");
        updateSettingsList();
        settingsProvider.saveSettingsToFile(new File("settings.xml"), settingsList);
        installSettings();
        String content = this.contentCreator.createContent(employeesList);
        try {
            this.mailApi.sendMail(
                    settingsProvider.getSettings("targetMail"),
                    settingsProvider.getSettings("emailHeader"),
                    content);
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    public void save(ActionEvent actionEvent) {
        System.out.println("____Save");

        updateSettingsList();
        settingsProvider.saveSettingsToFile(new File("settings.xml"), settingsList);
    }

    private void installSettings() {
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
    }

    public void settings(ActionEvent actionEvent) {
        if (settingsPanel.isVisible()) {
            settingsPanel.setVisible(false);
        } else {
            settingsPanel.setVisible(true);
        }
    }

    public void closeSettings(ActionEvent actionEvent) {
        settingsPanel.setVisible(false);

    }
}
