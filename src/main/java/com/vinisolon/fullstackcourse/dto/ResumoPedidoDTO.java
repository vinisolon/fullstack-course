package com.vinisolon.fullstackcourse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ResumoPedidoDTO {

    private String nomeCliente;
    private String documentoCliente;
    private Long numeroPedido;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataRealizacaoPedido;
    private Double valorTotalPedido;
    List<ProdutoResumoPedidoDTO> produtosPedido = new ArrayList<>();

    public ResumoPedidoDTO(String nomeCliente,
                           String documentoCliente,
                           Long numeroPedido,
                           Date dataRealizacaoPedido,
                           Double valorTotalPedido) {
        this.nomeCliente = nomeCliente;
        this.documentoCliente = documentoCliente;
        this.numeroPedido = numeroPedido;
        this.dataRealizacaoPedido = dataRealizacaoPedido;
        this.valorTotalPedido = valorTotalPedido;
    }

    public void setEachProduto(ProdutoResumoPedidoDTO produtoDTO) {
        produtosPedido.add(produtoDTO);
    }

}
