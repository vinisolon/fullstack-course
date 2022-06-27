package com.vinisolon.fullstackcourse.services.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

@Slf4j
public class MockEmailService extends AbstractEmailService {

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de e-mail...");
        log.info("\n{}", msg.toString());
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Simulando envio de e-mail html...");
        log.info("\n{}", msg.toString());
    }

}
