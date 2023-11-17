package com.example.fastfood.service;

import com.example.fastfood.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(User user, String url) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tranchicong08041998@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Please verify your registration");

        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>";
//                + "Your company name.";
        content = content.replace("[[name]]", user.getName());
        content = content.replace("[[URL]]", url);
        helper.setText(content,true);

        javaMailSender.send(message);
    }
}
