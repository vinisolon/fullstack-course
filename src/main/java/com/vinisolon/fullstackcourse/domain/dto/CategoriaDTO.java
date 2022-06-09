package com.vinisolon.fullstackcourse.domain.dto;

import com.vinisolon.fullstackcourse.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = -7667195585332356553L;

    private Long id;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 80, message = "Deve ter entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

}
