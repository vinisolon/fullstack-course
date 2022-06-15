package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@JsonTypeName("cartao")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoCartao extends Pagamento {

    private static final long serialVersionUID = 6090274115729221091L;

    private Integer numeroDeParcelas;

    public PagamentoCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

}
