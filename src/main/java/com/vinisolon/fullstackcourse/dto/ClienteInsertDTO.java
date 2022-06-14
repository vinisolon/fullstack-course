package com.vinisolon.fullstackcourse.dto;

import com.vinisolon.fullstackcourse.services.validation.annotations.ClienteInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ClienteInsert
public class ClienteInsertDTO implements Serializable {

    private static final long serialVersionUID = -7205047670686723872L;

    private static final String NOT_BLANK_DEFAULT_MSG = "Preenchimento obrigatório";

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    @Size(min = 5, max = 120, message = "Deve ter entre 5 e 80 caracteres")
    private String nome;

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    private String documento;

    private Integer tipo;

    private Set<String> telefones = new HashSet<>();

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    private String logradouro;

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    private String numero;

    private String bairro;

    private String complemento;

    @NotBlank(message = NOT_BLANK_DEFAULT_MSG)
    private String cep;

    private Long cidadeId;

}
