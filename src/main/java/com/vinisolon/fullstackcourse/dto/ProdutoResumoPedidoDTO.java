package com.vinisolon.fullstackcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoResumoPedidoDTO {

    private String nomeProduto;
    private Integer quantidadeProduto;
    private Double precoProduto;

}
