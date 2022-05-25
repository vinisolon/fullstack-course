package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.ItemPedido;
import com.vinisolon.fullstackcourse.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
