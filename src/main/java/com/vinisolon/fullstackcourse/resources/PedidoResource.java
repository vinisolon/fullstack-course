package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pedidos/")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> findPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.findPedidoById(id));
    }
}
