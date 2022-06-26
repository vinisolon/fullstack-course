package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.Produto;
import com.vinisolon.fullstackcourse.dto.ProdutoDTO;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import com.vinisolon.fullstackcourse.repositories.ProdutoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto findProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! ID: " + id));
    }

    public Page<ProdutoDTO> searchProdutosPaged(String nome, List<Long> idsCategorias, Integer page, Integer pageSize, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(idsCategorias);
        return produtoRepository.searchProdutosPaged(nome, categorias, pageRequest).map(ProdutoDTO::new);
    }

}
