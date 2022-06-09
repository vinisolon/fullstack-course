package com.vinisolon.fullstackcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinisolon.fullstackcourse.domain.enums.TipoCliente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -5275723090616785955L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String documento;
    private Integer tipo;

    @ElementCollection
    @CollectionTable(name = "telefone")
    @Column(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Endereco> enderecos = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    @Column(name = "pedido")
    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente(Long id, String nome, String email, String documento, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.tipo = (tipo == null ? null : tipo.getCodigo());
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(this.tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCodigo();
    }

}
