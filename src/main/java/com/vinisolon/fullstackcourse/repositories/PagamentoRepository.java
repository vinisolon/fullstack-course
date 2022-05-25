package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
