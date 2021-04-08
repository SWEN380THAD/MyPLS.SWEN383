package com.springexample.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

    private JavaMailSender mailSender;
    private MailContentBuilder builder;

    @Autowired
    public MailClient(JavaMailSender mailSender, MailContentBuilder builder) {
        this.mailSender = mailSender;
        this.builder = builder;
    }


    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("admin@mypls.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");
            String content = builder.build(message);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            System.out.println(e);
            // runtime exception; compiler will not force you to handle it
        }
    }


}
