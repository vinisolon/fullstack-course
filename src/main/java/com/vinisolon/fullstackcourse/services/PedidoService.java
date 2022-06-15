package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.ItemPedido;
import com.vinisolon.fullstackcourse.domain.PagamentoBoleto;
import com.vinisolon.fullstackcourse.domain.Pedido;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import com.vinisolon.fullstackcourse.dto.PedidoPdfExportDTO;
import com.vinisolon.fullstackcourse.dto.ProdutoPedidoPdfExportDTO;
import com.vinisolon.fullstackcourse.repositories.ItemPedidoRepository;
import com.vinisolon.fullstackcourse.repositories.PagamentoRepository;
import com.vinisolon.fullstackcourse.repositories.PedidoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    BoletoService boletoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

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

    public Pedido insertPedido(Pedido pedido2Insert) {
        // Pedido
        pedido2Insert.setDataRealizacao(new Date());
        pedido2Insert.setCliente(clienteService.findClienteById(pedido2Insert.getCliente().getId()));
        pedido2Insert.setEnderecoDeEntrega(enderecoService.findEnderecoById(pedido2Insert.getEnderecoDeEntrega().getId()));

        // Pagamento
        pedido2Insert.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido2Insert.getPagamento().setPedido(pedido2Insert);
        if (pedido2Insert.getPagamento() instanceof PagamentoBoleto)
            boletoService.setDataVencimento((PagamentoBoleto) pedido2Insert.getPagamento(), pedido2Insert.getDataRealizacao());

        // Itens
        for (ItemPedido item : pedido2Insert.getItens()) {
            item.setProduto(produtoService.findProdutoById(item.getProduto().getId()));
            item.setPedido(pedido2Insert);
            item.setPreco(item.getProduto().getPreco());
            item.setDesconto(0.0);
        }

        Pedido pedidoCreated = pedidoRepository.save(pedido2Insert);
        pagamentoRepository.save(pedido2Insert.getPagamento());
        itemPedidoRepository.saveAll(pedido2Insert.getItens());

        return pedidoCreated;
    }

}
