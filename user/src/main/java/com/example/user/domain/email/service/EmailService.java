package com.example.user.domain.email.service;

import com.example.user.domain.email.domain.Email;
import com.example.user.domain.email.exception.EmailSendFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String senderEmail;
    private final JavaMailSender javaMailSender;

    public void sendMail(Email mail, String token) throws EmailSendFailedException {
        Integer randomNum = createNumber();
        MimeMessage message = createMail(mail.getEmail(), randomNum);
        javaMailSender.send(message);
    }

    public Integer createNumber() {
        return (int) ((Math.random() * (90000)) + 100000);
    }

    public MimeMessage createMail(String mail, Integer randomNum) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("epi 이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + randomNum + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            throw new EmailSendFailedException();
        }

        return message;
    }
}
