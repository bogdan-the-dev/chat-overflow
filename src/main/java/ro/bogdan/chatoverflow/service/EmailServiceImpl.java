package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String destination, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bogdan.auto.group@gmail.com");
        message.setTo(destination);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
