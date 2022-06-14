package com.vinisolon.fullstackcourse.services.validation.validators;

import com.vinisolon.fullstackcourse.domain.Cliente;
import com.vinisolon.fullstackcourse.dto.ClienteDTO;
import com.vinisolon.fullstackcourse.repositories.ClienteRepository;
import com.vinisolon.fullstackcourse.resources.exceptions.FieldMessage;
import com.vinisolon.fullstackcourse.services.validation.annotations.ClienteUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> errors = new ArrayList<>();

        Cliente searchCliente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (searchCliente != null && !searchCliente.getId().equals(clienteDTO.getId()))
            errors.add(new FieldMessage("email", "email pertence a outro cliente"));

        for (FieldMessage err : errors) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(err.getMessage())
                    .addPropertyNode(err.getFieldName())
                    .addConstraintViolation();
        }

        return errors.isEmpty();
    }

}
