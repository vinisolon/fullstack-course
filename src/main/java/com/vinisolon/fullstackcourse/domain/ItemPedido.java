package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = -7328054112416172381L;

    @JsonIgnore
    @EmbeddedId // Chave primaria composta por subtipo
    private ItemPedidoPK id = new ItemPedidoPK();
    private Integer quantidade;
    private Double preco;
    private Double desconto;

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco, Double desconto) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
    }

    // Operações diretas para acessar os pedidos e produtos sem ter que acessar id primeiro
    @JsonIgnore
    public Pedido getPedido() {
        return this.id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        this.id.setPedido(pedido);
    }

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    public double getSubTotal() {
        return (this.preco - this.desconto) * this.quantidade;
    }

    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuffer sb = new StringBuffer();
        sb.append(getProduto().getNome());
        sb.append(", qnt ").append(getQuantidade());
        sb.append(", valor un ").append(nf.format(getPreco()));
        sb.append(", subtotal ").append(nf.format(getSubTotal()));
        sb.append("\n");
        return sb.toString();
    }

}
