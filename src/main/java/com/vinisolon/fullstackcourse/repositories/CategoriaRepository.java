package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
