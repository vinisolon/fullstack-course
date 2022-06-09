package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Cidade;
import com.vinisolon.fullstackcourse.domain.Cliente;
import com.vinisolon.fullstackcourse.domain.Endereco;
import com.vinisolon.fullstackcourse.domain.dto.ClienteDTO;
import com.vinisolon.fullstackcourse.domain.dto.ClienteInsertDTO;
import com.vinisolon.fullstackcourse.domain.enums.TipoCliente;
import com.vinisolon.fullstackcourse.repositories.ClienteRepository;
import com.vinisolon.fullstackcourse.repositories.EnderecoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.DataIntegrityException;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

    @Transactional
    public Cliente insertCliente(ClienteInsertDTO clienteToInsert) {
        Cliente toInsert = clienteRepository.save(clienteFromDto(clienteToInsert));
        enderecoRepository.saveAll(toInsert.getEnderecos());
        return toInsert;
    }

    public void updateCliente(ClienteDTO clienteToUpdate) {
        Cliente toUpdate = findClienteById(clienteToUpdate.getId());
        Cliente newData = clienteFromDto(clienteToUpdate);
        updateClienteData(toUpdate, newData);
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

    private Cliente clienteFromDto(ClienteInsertDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getDocumento(), TipoCliente.toEnum(dto.getTipo()));
        dto.getTelefones().forEach(tel -> cliente.getTelefones().add(tel));
        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getBairro(), dto.getComplemento(), dto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        return cliente;
    }

    private void updateClienteData(Cliente toUpdate, Cliente newData) {
        toUpdate.setNome(newData.getNome());
        toUpdate.setEmail(newData.getEmail());
    }

}
