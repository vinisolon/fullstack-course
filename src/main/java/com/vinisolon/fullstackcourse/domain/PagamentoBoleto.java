package com.vinisolon.fullstackcourse.domain;

import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoBoleto extends Pagamento {

    private static final long serialVersionUID = 1774795851328081858L;

    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
