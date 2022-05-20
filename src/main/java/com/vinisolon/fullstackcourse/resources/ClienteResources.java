package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/clientes/")
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> findClienteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findClienteById(id));
    }
}
