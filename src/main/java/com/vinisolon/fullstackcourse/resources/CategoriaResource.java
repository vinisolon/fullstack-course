package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/categorias/")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> findCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.findCategoriaById(id));
    }
}
