package com.gmail.wizaripost.snitch.mail;

//import javax.mail.MessagingException;
//import java.io.File;

public class Mail {

//    public static void main(String[] args) {
//        IAuthenticatorProvider authenticatorProvider = new AuthenticatorProviderFromMemory();
//        IPropertiesProvider propertiesProvider = new PropertiesProviderFromMemory();
//        ISenderProvider senderProvider = new SenderProviderFromMemory();
//
//        IMailApi mailApi = new MailApi(senderProvider, propertiesProvider, authenticatorProvider);
////        IEmployeeProvider employeeProvider = new EmployeeProviderFromMemory();
//        IEmployeeProvider employeeProvider = new EmployeeProviderFromXml(new File("employees.xml"));
//
//        IContentCreator contentCreator = new ContentCreator();
//        String content = contentCreator.createContent(employeeProvider.getEmployees("104"));
//
//
//        try {
//            mailApi.sendMail("xxxx@gmail.com", "Hello wold", content);
//        } catch (MessagingException e) {
//            System.out.println("Произошла ошибка " + e.getMessage());
//        }
//
//    }

    /**
     static Properties properties = new Properties();
     static {
     properties.put("mail.smtp.host", "smtp.gmail.com");
     properties.put("mail.smtp.port", "587");
     properties.put("mail.smtp.auth", "true");
     properties.put("mail.smtp.starttls.enable", "true");
     }
     public static void main(String[] args) {
     String returnStatement = null;
     try {
     Authenticator auth = new Authenticator() {
     public PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication("xxx@gmail.com", "xxx");
     }
     };
     Session session = Session.getInstance(properties, auth);
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("xxxx@gmail.com"));
     message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxxx@gmail.com"));
     message.setSentDate(new Date());
     message.setSubject("Test Mail");
     Date dateNow = new Date();
     SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
     //            ArrayList workers2 =
     String hi = "Здравствуйте, Лариса!";
     //            message.setText();
     message.setText(hi + "\n"
     + formatForDateNow.format(dateNow) + "\n" + "\n"
     + "1488 отдел!"+ "\n"
     + Test.Wor());
     //            message.setText("Hi");
     returnStatement = "The e-mail was sent successfully";
     System.out.println(returnStatement);
     Transport.send(message);
     } catch (Exception e) {
     returnStatement = "error in sending mail";
     e.printStackTrace();
     }
     }

     */
}
