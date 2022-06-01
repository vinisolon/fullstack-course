package com.vinisolon.fullstackcourse.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoPdfExportDTO {

    private String nomeCliente;
    private String documentoCliente;
    private Long numeroPedido;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataRealizacaoPedido;
    private Double valorTotalPedido;
    List<ProdutoPedidoPdfExportDTO> produtosPedido = new ArrayList<>();

    public PedidoPdfExportDTO(String nomeCliente,
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

    public void setEachProduto(ProdutoPedidoPdfExportDTO produtoDTO) {
        produtosPedido.add(produtoDTO);
    }

}
