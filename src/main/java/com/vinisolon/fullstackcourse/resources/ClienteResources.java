package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Cliente;
import com.vinisolon.fullstackcourse.dto.ClienteDTO;
import com.vinisolon.fullstackcourse.dto.ClienteInsertDTO;
import com.vinisolon.fullstackcourse.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        return ResponseEntity.ok().body(clienteService.findAllClientes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findClienteById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Void> insertCliente(@Valid @RequestBody ClienteInsertDTO clienteToInsert) {
        Cliente clienteCreated = clienteService.insertCliente(clienteToInsert);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
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
    public ResponseEntity<Page<ClienteDTO>> findAllClientesPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "24") Integer pageSize,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        return ResponseEntity.ok().body(clienteService.findAllClientesPaged(page, pageSize, direction, orderBy));
    }

}
