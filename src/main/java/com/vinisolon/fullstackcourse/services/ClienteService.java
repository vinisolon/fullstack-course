package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Cliente;
import com.vinisolon.fullstackcourse.domain.dto.ClienteDTO;
import com.vinisolon.fullstackcourse.repositories.ClienteRepository;
import com.vinisolon.fullstackcourse.services.exceptions.DataIntegrityException;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<?> findAllClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
    }

    public void updateCliente(ClienteDTO clienteToUpdate) {
        Cliente existing = findClienteById(clienteToUpdate.getId());
        Cliente toUpdate = clienteFromDto(clienteToUpdate);
        updateClienteData(toUpdate, existing);
        clienteRepository.save(toUpdate);
    }

    public void deleteClienteById(Long id) {
        findClienteById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar porque existe entidades relacionadas!");
        }
    }

    public Page<?> findAllClientesPaged(Integer page, Integer pageSize, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest).map(ClienteDTO::new);
    }

    private Cliente clienteFromDto(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }

    private void updateClienteData(Cliente toUpdate, Cliente existing) {
        toUpdate.setDocumento(existing.getDocumento());
        toUpdate.setTipo(existing.getTipo());
    }

}
