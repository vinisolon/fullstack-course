package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "api/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.findCategoriaById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Void> insertCategoria(@RequestBody Categoria categoriaToInsert) {
        Categoria categoriaCreated = categoriaService.insertCategoria(categoriaToInsert);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateCategoria(@RequestBody Categoria categoriaToUpdate) {
        categoriaService.updateCategoria(categoriaToUpdate);
        return ResponseEntity.noContent().build();
    }

}
