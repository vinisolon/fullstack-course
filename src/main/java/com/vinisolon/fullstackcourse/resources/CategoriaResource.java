package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.dto.CategoriaDTO;
import com.vinisolon.fullstackcourse.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAllCategorias() {
        return ResponseEntity.ok().body(categoriaService.findAllCategorias());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.findCategoriaById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Void> insertCategoria(@Valid @RequestBody CategoriaDTO categoria2Insert) {
        Categoria categoriaCreated = categoriaService.insertCategoria(categoria2Insert);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateCategoria(@Valid @RequestBody CategoriaDTO categoria2Update) {
        categoriaService.updateCategoria(categoria2Update);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/paged")
    public ResponseEntity<Page<CategoriaDTO>> findAllCategoriasPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "24") Integer pageSize,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        return ResponseEntity.ok().body(categoriaService.findAllCategoriasPaged(page, pageSize, direction, orderBy));
    }

}
