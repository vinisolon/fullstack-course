package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Cliente;
import com.vinisolon.fullstackcourse.repositories.ClienteRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
    }
}
