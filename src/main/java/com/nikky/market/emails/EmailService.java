package com.nikky.market.emails;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private static JavaMailSender mailSender;

    @Async
    public  static void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        // for  multiple recipients
        // message.setTo(new String[] {"recipient1@example.com", "recipient2@example.com", "recipient3@example.com"});
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("example@gmail.com");
        mailSender.send(message);
    }

    public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from Spring");

        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendEmailFromTemplate() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        // Read the HTML template into a String variable
        String htmlTemplate = readFile("template.html");

        // Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
        htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    private String readFile(String s) {
        return s;
    }

    public void sendEmailWithAttachment(String to, String subject, String body) throws MessagingException, IOException {


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message , true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
        helper.addAttachment("attachment.jpg", file);

        mailSender.send(message);
    }



}




//    String recipient = "john.doe@example.com";
//    String subject = "Hello, ${firstName}!";
//    String template = "Hello, ${firstName}!\n\n"
//            + "This is a message just for you, ${firstName} ${lastName}. "
//            + "We hope you're having a great day!\n\n"
//            + "Best regards,\n"
//            + "The Spring Boot Team";
//
//    Map<String, Object> variables = new HashMap<>();
//variables.put("firstName", "John");
//        variables.put("lastName", "Doe");
//
//        sendEmail(recipient, subject, template, variables);