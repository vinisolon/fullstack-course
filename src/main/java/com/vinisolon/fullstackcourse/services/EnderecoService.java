package com.vinisolon.fullstackcourse.services;

import com.vinisolon.fullstackcourse.domain.Endereco;
import com.vinisolon.fullstackcourse.repositories.EnderecoRepository;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco findEnderecoById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado! ID: " + id));
    }

}
