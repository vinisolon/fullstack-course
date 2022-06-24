package com.vinisolon.fullstackcourse.services.email;

import com.vinisolon.fullstackcourse.domain.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

@Slf4j
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.email.sender}")
    private String emailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
        log.info("E-mail de confirmação do pedido enviado");
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(emailSender);
        sm.setSubject("CONFIRMAÇÃO PEDIDO Nº " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }

}
