package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
