package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.ItemPedido;
import com.vinisolon.fullstackcourse.domain.PagamentoBoleto;
import com.vinisolon.fullstackcourse.domain.Pedido;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import com.vinisolon.fullstackcourse.dto.ProdutoResumoPedidoDTO;
import com.vinisolon.fullstackcourse.dto.ResumoPedidoDTO;
import com.vinisolon.fullstackcourse.repositories.ItemPedidoRepository;
import com.vinisolon.fullstackcourse.repositories.PagamentoRepository;
import com.vinisolon.fullstackcourse.repositories.PedidoRepository;
import com.vinisolon.fullstackcourse.services.email.EmailService;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontado! ID: " + id));
    }

    public ResumoPedidoDTO getPedidoPdfExportDto(Long idPedido) {
        return pedidoToPedidoPdfExport(
                pedidoRepository.findById(idPedido)
                        .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontado! ID: " + idPedido))
        );
    }

    private ResumoPedidoDTO pedidoToPedidoPdfExport(Pedido pedido) {
        ResumoPedidoDTO pedidoToPdf = new ResumoPedidoDTO(
                pedido.getCliente().getNome(),
                pedido.getCliente().getDocumento(),
                pedido.getId(),
                pedido.getDataRealizacao(),
                pedido.getValorTotal()
        );

        for (ItemPedido item : pedido.getItens())
            pedidoToPdf.setEachProduto(
                    new ProdutoResumoPedidoDTO(
                            item.getProduto().getNome(),
                            item.getQuantidade(),
                            item.getPreco()
                    )
            );

        return pedidoToPdf;
    }

    @Transactional
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

//        emailService.sendOrderConfirmationEmail(pedidoCreated);
        emailService.sendOrderConfirmationHtmlEmail(pedidoCreated);

        return pedidoCreated;
    }

}
