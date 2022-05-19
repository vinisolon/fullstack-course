package com.vinisolon.fullstackcourse.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    public static TipoCliente toEnum(Integer codigo) {

        if (codigo == null)
            throw new NullPointerException("Código nulo para TipoCliente!");

        for (TipoCliente tipoCliente : TipoCliente.values()) {
            if (codigo.equals(tipoCliente.getCodigo()))
                return tipoCliente;
        }

        throw new IllegalArgumentException("Código (" + codigo + ") de TipoCliente não existe!");
    }
}
