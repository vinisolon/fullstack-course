package com.vinisolon.fullstackcourse.services.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
public class MockEmailService extends AbstractEmailService {

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de e-mail da confirmação do pedido...");
        log.info("\n{}", msg.toString());
    }

}
