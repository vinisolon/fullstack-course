package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.dto.CategoriaDTO;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import com.vinisolon.fullstackcourse.services.exceptions.DataIntegrityViolationException;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<?> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
    }

    public Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! ID: " + id));
    }

    public Categoria insertCategoria(Categoria categoriaToInsert) {
        return categoriaRepository.save(categoriaToInsert);
    }

    public void updateCategoria(Categoria categoriaToUpdate) {
        findCategoriaById(categoriaToUpdate.getId());
        categoriaRepository.save(categoriaToUpdate);
    }

    public void deleteCategoriaById(Long id) {
        findCategoriaById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível deletar categorias associadas a produto(s)!");
        }
    }

}
