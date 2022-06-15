package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@JsonTypeName("boleto")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoBoleto extends Pagamento {

    private static final long serialVersionUID = 1774795851328081858L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataPagamento;

    public PagamentoBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

}
