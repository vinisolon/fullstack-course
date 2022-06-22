package com.vinisolon.fullstackcourse.services.email;

import com.vinisolon.fullstackcourse.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

}
