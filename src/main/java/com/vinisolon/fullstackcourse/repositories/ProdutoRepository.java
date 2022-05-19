package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
