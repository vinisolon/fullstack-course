package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.domain.Pedido;
import com.vinisolon.fullstackcourse.files.export.ExportToPDF;
import com.vinisolon.fullstackcourse.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ExportToPDF exportToPDF;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.findPedidoById(id));
    }

    @GetMapping(value = "/export/pdf/{id}", produces = "application/pdf")
    public void exportPedidoPdf(@PathVariable Long id, HttpServletResponse response) {
        exportToPDF.export(pedidoService.getPedidoPdfExportDto(id), response);
//        exportToPDF.exportBase64(pedidoService.getPedidoPdfExportDto(id), response);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Void> insertPedido(@Valid @RequestBody Pedido pedido2Insert) {
        Pedido pedidoCreated = pedidoService.insertPedido(pedido2Insert);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedidoCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
