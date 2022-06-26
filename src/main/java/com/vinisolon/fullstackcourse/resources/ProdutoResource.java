package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Produto;
import com.vinisolon.fullstackcourse.dto.ProdutoDTO;
import com.vinisolon.fullstackcourse.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAllProdutos() {
        return ResponseEntity.ok().body(produtoService.findAllProdutos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.findProdutoById(id));
    }

    @GetMapping(value = "/paged")
    public ResponseEntity<Page<ProdutoDTO>> searchProdutosPaged(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") List<Long> idsCategorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pagesize", defaultValue = "24") Integer pageSize,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderby", defaultValue = "nome") String orderBy) {

        return ResponseEntity.ok().body(produtoService.searchProdutosPaged(nome, idsCategorias, page, pageSize, direction, orderBy));
    }

}
