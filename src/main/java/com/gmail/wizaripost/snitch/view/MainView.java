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
import java.util.ArrayList;
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
    public AnchorPane settingsPanel1;
    public TextField CCMail;
    public TextField CCMail1;
    public TextField CCMail2;
    public TextField BCCMail;
    public TextField BCCMail1;
    public TextField BCCMail2;

    private IMailApi mailApi;
    private IContentCreator contentCreator;
    private IEmployeeProvider employeeProvider;
    @Getter
    public ISettingsProvider settingsProvider;
    private List<Employee> employeesList;
    private List<Settings> settingsList;
//    private ArrayList<String> recipient = new ArrayList<>();
    private ArrayList<String> CCRecipient = new ArrayList<>();
    private ArrayList<String> BCCRecipient = new ArrayList<>();
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
        updateMailList();
    }

    private void updateFields() {
        this.myMail.setText(settingsProvider.getSettings("myMail"));
        this.passwordMyMail.setText(settingsProvider.getSettings("passwordMyMail"));
        this.targetMail.setText(settingsProvider.getSettings("targetMail"));
        this.CCMail.setText(settingsProvider.getSettings("CCMail"));
        this.CCMail1.setText(settingsProvider.getSettings("CCMail1"));
        this.CCMail2.setText(settingsProvider.getSettings("CCMail2"));
        this.BCCMail.setText(settingsProvider.getSettings("BCCMail"));
        this.BCCMail1.setText(settingsProvider.getSettings("BCCMail1"));
        this.BCCMail2.setText(settingsProvider.getSettings("BCCMail2"));

        this.mailSmtpHost.setText(settingsProvider.getSettings("mail.smtp.host"));
        this.mailSmtpPort.setText(settingsProvider.getSettings("mail.smtp.port"));
        this.mailSmtpAuth.setText(settingsProvider.getSettings("mail.smtp.auth"));
        this.mailSmtpStarttlsEnable.setText(settingsProvider.getSettings("mail.smtp.starttls.enable"));

        this.emailHeader.setText(settingsProvider.getSettings("emailHeader"));
        this.emailBody.setText(settingsProvider.getSettings("emailBody"));
        this.emailBottom.setText(settingsProvider.getSettings("emailBottom"));
    }

    private void updateSettingsList() {
        settingsList.clear();
        settingsList.add(new Settings("myMail", myMail.getText()));
        settingsList.add(new Settings("passwordMyMail", passwordMyMail.getText()));
        settingsList.add(new Settings("targetMail", targetMail.getText()));
        settingsList.add(new Settings("CCMail", CCMail.getText()));
        settingsList.add(new Settings("CCMail1", CCMail1.getText()));
        settingsList.add(new Settings("CCMail2", CCMail2.getText()));
        settingsList.add(new Settings("BCCMail", BCCMail.getText()));
        settingsList.add(new Settings("BCCMail1", BCCMail1.getText()));
        settingsList.add(new Settings("BCCMail2", BCCMail2.getText()));

        settingsList.add(new Settings("mail.smtp.host", mailSmtpHost.getText()));
        settingsList.add(new Settings("mail.smtp.port", mailSmtpPort.getText()));
        settingsList.add(new Settings("mail.smtp.auth", mailSmtpAuth.getText()));
        settingsList.add(new Settings("mail.smtp.starttls.enable",
                mailSmtpStarttlsEnable.getText()));

        settingsList.add(new Settings("emailHeader", emailHeader.getText()));
        settingsList.add(new Settings("emailBody", emailBody.getText()));
        settingsList.add(new Settings("emailBottom", emailBottom.getText()));
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
        updateMailList();
        String content = this.contentCreator.createContent(employeesList);
        try {
            this.mailApi.sendMail(
                    settingsProvider.getSettings("targetMail"),
                    CCRecipient,
                    BCCRecipient,
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
        settingsPanel.setVisible(true);
        settingsPanel1.setVisible(false);
    }

    public void mailBodySettings(ActionEvent actionEvent) {
        settingsPanel1.setVisible(true);
        settingsPanel.setVisible(false);
    }

    public void closeSettings(ActionEvent actionEvent) {
        settingsPanel.setVisible(false);
        settingsPanel1.setVisible(false);
    }

    private void updateMailList() {
        CCRecipient.clear();
        BCCRecipient.clear();
//        CCRecipient.add(CCMail.getText());
//        CCRecipient.add(CCMail1.getText());
//        CCRecipient.add(CCMail2.getText());
//        BCCRecipient.add(BCCMail.getText());
//        BCCRecipient.add(BCCMail1.getText());
//        BCCRecipient.add(BCCMail2.getText());
        CCRecipient.add(settingsProvider.getSettings("CCMail"));
        CCRecipient.add(settingsProvider.getSettings("CCMail1"));
        CCRecipient.add(settingsProvider.getSettings("CCMail2"));
        BCCRecipient.add(settingsProvider.getSettings("BCCMail"));
        BCCRecipient.add(settingsProvider.getSettings("BCCMail1"));
        BCCRecipient.add(settingsProvider.getSettings("BCCMail2"));


//        if (settingsProvider.getSettings("CCMail") != null && !settingsProvider.getSettings("CCMail").isEmpty()) {
//            CCRecipient.add(settingsProvider.getSettings("CCMail"));
//        }
//        if (settingsProvider.getSettings("CCMail1") != null && !settingsProvider.getSettings("CCMail1").isEmpty()) {
//            CCRecipient.add(settingsProvider.getSettings("CCMail1"));
//
//        }
//        if (settingsProvider.getSettings("CCMail2") != null && !settingsProvider.getSettings("CCMail2").isEmpty()) {
//            CCRecipient.add(settingsProvider.getSettings("CCMail2"));
//
//        }
//        if (settingsProvider.getSettings("BCCMail") != null && !settingsProvider.getSettings("BCCMail").isEmpty()) {
//            BCCRecipient.add(settingsProvider.getSettings("BCCMail"));
//
//        }
//        if (settingsProvider.getSettings("BCCMail1") != null && !settingsProvider.getSettings("BCCMail1").isEmpty()) {
//            BCCRecipient.add(settingsProvider.getSettings("BCCMail1"));
//
//        }
//        if (settingsProvider.getSettings("BCCMail2") != null && !settingsProvider.getSettings("BCCMail2").isEmpty()) {
//            BCCRecipient.add(settingsProvider.getSettings("BCCMail2"));
//        }
    }
}
