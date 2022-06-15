package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void setDataVencimento(PagamentoBoleto pagamento, Date dataRealizacaoPedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataRealizacaoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7); // Data de vencimento do boleto = 7 dias após realização da compra
        pagamento.setDataVencimento(cal.getTime());
    }

}
