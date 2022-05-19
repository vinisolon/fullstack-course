package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
