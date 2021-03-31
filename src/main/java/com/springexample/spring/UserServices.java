package com.springexample.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class UserServices {



    private JavaMailSender mailSender;





    public void register(User user, String siteURL) {

    }

    public void sendVerificationEmail(User user, String siteURL) {


        try {

            String toAddress = user.getEmail();
            String fromAddress = "Your email address";
            String senderName = "Your company name";
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "Your company name.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", "TEST This");
            String verifyURL = siteURL + "/verify?code=" + user.getVerification_code();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            mailSender.send(message);
        }catch(Exception ex){
            System.out.println(ex);
        }

    }

}
