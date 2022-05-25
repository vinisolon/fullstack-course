package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Pedido;
import com.vinisolon.fullstackcourse.repositories.PedidoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontado! ID: " + id));
    }
}
