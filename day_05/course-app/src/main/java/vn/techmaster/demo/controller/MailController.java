package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    JavaMailSender javaMailSender;
    @PostMapping("/send-simple-email")
    public String sendSimpleEmail() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("abc@gmail.com");
        message.setSubject("cong");
        message.setText("Hello World1");

        // Send Message!
        javaMailSender.send(message);

        return "Email Sent!";
    }
}
