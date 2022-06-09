package com.vinisolon.fullstackcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInsertDTO implements Serializable {

    private static final long serialVersionUID = -7205047670686723872L;

    private String nome;
    private String email;
    private String documento;
    private Integer tipo;

    private Set<String> telefones = new HashSet<>();

    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;

    private Long cidadeId;

}
