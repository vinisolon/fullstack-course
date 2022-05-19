package com.vinisolon.fullstackcourse.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/categorias/")
public class CategoriaResource {

    @GetMapping(value = "test")
    public String test() {
        return "GET: REST Funcionando...";
    }
}
