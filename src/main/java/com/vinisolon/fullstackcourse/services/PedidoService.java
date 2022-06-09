package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.ItemPedido;
import com.vinisolon.fullstackcourse.domain.Pedido;
import com.vinisolon.fullstackcourse.dto.PedidoPdfExportDTO;
import com.vinisolon.fullstackcourse.dto.ProdutoPedidoPdfExportDTO;
import com.vinisolon.fullstackcourse.repositories.PedidoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontado! ID: " + id));
    }

    public PedidoPdfExportDTO getPedidoPdfExportDto(Long idPedido) {
        return pedidoToPedidoPdfExport(
                pedidoRepository.findById(idPedido)
                        .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontado! ID: " + idPedido))
        );
    }

    private PedidoPdfExportDTO pedidoToPedidoPdfExport(Pedido pedido) {
        PedidoPdfExportDTO pedidoToPdf = new PedidoPdfExportDTO(
                pedido.getCliente().getNome(),
                pedido.getCliente().getDocumento(),
                pedido.getId(),
                pedido.getDataRealizacao(),
                pedido.getValorTotalPedido()
        );

        for (ItemPedido item : pedido.getItens())
            pedidoToPdf.setEachProduto(
                    new ProdutoPedidoPdfExportDTO(
                            item.getProduto().getNome(),
                            item.getQuantidade(),
                            item.getPreco()
                    )
            );

        return pedidoToPdf;
    }

}
