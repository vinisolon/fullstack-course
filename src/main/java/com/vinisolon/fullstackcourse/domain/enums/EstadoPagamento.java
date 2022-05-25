package com.vinisolon.fullstackcourse.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descricao;

    public static EstadoPagamento toEnum(Integer codigo) {

        if (codigo == null)
            throw new NullPointerException("Código nulo para EstadoPagamento!");

        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if (codigo.equals(estadoPagamento.getCodigo()))
                return estadoPagamento;
        }

        throw new IllegalArgumentException("Código (" + codigo + ") de EstadoPagamento não existe!");
    }
}
