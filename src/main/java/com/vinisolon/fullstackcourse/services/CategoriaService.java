package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
