package com.vinisolon.fullstackcourse.resources;

import com.vinisolon.fullstackcourse.files.export.ExportToPDF;
import com.vinisolon.fullstackcourse.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @GetMapping(value = "/export/pdf/{id}")
    public void exportPedidoPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        exportToPDF.export(pedidoService.getPedidoPdfExportDto(id), response);
    }

}
