package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 2649684577780289776L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataRealizacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco enderecoDeEntrega;

    // Necessário para salvar pedido/pagamento
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Long id, Date dataRealizacao, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.dataRealizacao = dataRealizacao;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public double getValorTotal() {
        double valorTotalPedido = 0.0;
        for (ItemPedido item : this.itens)
            valorTotalPedido += item.getSubTotal();
        return valorTotalPedido;
    }

    @Override
    public String toString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Pedido número ").append(getId());
        sb.append(", ").append(sdf.format(getDataRealizacao()));
        sb.append(", ").append(getCliente().getNome());
        sb.append(", Pagamento ").append(getPagamento().getEstado().getDescricao());
        sb.append("\n");
        for (ItemPedido item : getItens())
            sb.append(item.toString());
        sb.append("Valor total ").append(nf.format(getValorTotal()));
        return sb.toString();
    }

}
