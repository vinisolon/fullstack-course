package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
