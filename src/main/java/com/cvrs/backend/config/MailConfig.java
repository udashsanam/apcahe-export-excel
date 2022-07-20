package com.cvrs.backend.config;

import com.cvrs.backend.util.CvrsUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(CvrsUtils.HOST);
        mailSender.setPort(CvrsUtils.PORT);
        mailSender.setProtocol(CvrsUtils.PROTOCOL);

        mailSender.setUsername(CvrsUtils.USERNAME);
        mailSender.setPassword(CvrsUtils.PASSWORD);

//        Properties mailProperties = mailSender.getJavaMailProperties();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailProperties.put("mail.debug", true);
//        mailProperties.put("mail.smtp.timeout", 5000);
//        mailProperties.put("mail.smtp.connectiontimeout", 5000);

        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }
}
