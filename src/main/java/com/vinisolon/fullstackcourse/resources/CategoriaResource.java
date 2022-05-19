package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/categorias/")
public class CategoriaResource {

    @GetMapping(value = "test-categorias")
    public List<Categoria> testCategorias() {
        return Arrays.asList(new Categoria(1L, "categoria 01"), new Categoria(2L, "categoria 02"));
    }
}
