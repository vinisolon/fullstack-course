package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.dto.ClienteDTO;
import com.vinisolon.fullstackcourse.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<?>> findAllClientes() {
        return ResponseEntity.ok().body(clienteService.findAllClientes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findClienteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findClienteById(id));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateCategoria(@Valid @RequestBody ClienteDTO clienteToUpdate) {
        clienteService.updateCliente(clienteToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteClienteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/paged")
    public ResponseEntity<Page<?>> findAllClientesPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "24") Integer pageSize,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        return ResponseEntity.ok().body(clienteService.findAllClientesPaged(page, pageSize, direction, orderBy));
    }

}
