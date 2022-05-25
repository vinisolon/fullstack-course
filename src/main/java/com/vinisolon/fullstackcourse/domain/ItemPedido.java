package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

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

    public Produto getProduto() {
        return this.id.getProduto();
    }
}
