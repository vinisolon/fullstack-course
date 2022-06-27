package com.vinisolon.fullstackcourse.services.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

@Slf4j
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender; // SimpleMailMessage

    @Autowired
    private JavaMailSender javaMailSender; // MimeMessage (html)

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Enviando e-mail de confirmação do pedido...");
        mailSender.send(msg);
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Enviando e-mail de confirmação do pedido...");
        javaMailSender.send(msg);
    }

}
