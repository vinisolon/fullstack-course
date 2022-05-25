package com.vinisolon.fullstackcourse.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    public Pedido(Long id, Date dataRealizacao, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.dataRealizacao = dataRealizacao;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }
}