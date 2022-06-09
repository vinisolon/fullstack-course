package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
// Anotação usada para mapear herança
@Inheritance(strategy = InheritanceType.JOINED) // Cria uma tabela para cada tipo de pagamento
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Cria um 'tabelão' e seta nulo nos atributos que não são do seu tipo
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 3546534848857395110L;

    @Id
    private Long id;
    private Integer estado;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // id entidade Pagamento = id entidade Pedido
    private Pedido pedido;

    public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = (estado == null ? null : estado.getCodigo());
        this.pedido = pedido;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCodigo();
    }
}
