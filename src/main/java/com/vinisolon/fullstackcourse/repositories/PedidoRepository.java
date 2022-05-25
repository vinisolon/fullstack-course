package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
