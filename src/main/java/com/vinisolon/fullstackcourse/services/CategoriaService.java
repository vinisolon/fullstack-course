package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.dto.CategoriaDTO;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import com.vinisolon.fullstackcourse.services.exceptions.DataIntegrityException;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<?> findAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
    }

    public Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! ID: " + id));
    }

    public Categoria insertCategoria(CategoriaDTO categoriaToInsert) {
        return categoriaRepository.save(categoriaFromDto(categoriaToInsert));
    }

    public void updateCategoria(CategoriaDTO categoriaToUpdate) {
        findCategoriaById(categoriaToUpdate.getId());
        categoriaRepository.save(categoriaFromDto(categoriaToUpdate));
    }

    public void deleteCategoriaById(Long id) {
        findCategoriaById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar categorias associadas a produto(s)!");
        }
    }

    public Page<?> findAllCategoriasPaged(Integer page, Integer pageSize, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest).map(CategoriaDTO::new);
    }

    private Categoria categoriaFromDto(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

}
