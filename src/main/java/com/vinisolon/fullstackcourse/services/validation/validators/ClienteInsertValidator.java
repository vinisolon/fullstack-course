package com.vinisolon.fullstackcourse.services.validation.validators;

import com.vinisolon.fullstackcourse.domain.enums.TipoCliente;
import com.vinisolon.fullstackcourse.dto.ClienteInsertDTO;
import com.vinisolon.fullstackcourse.resources.exceptions.FieldMessage;
import com.vinisolon.fullstackcourse.services.validation.annotations.ClienteInsert;
import com.vinisolon.fullstackcourse.services.validation.utils.DocumentosBR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteInsertDTO> {

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteInsertDTO clienteInsertDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> errors = new ArrayList<>();

        // CPF
        if (clienteInsertDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
                && !DocumentosBR.isValidCPF(clienteInsertDTO.getDocumento()))
            errors.add(new FieldMessage("documento", "CPF inválido"));

        // CNPJ
        if (clienteInsertDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
                && !DocumentosBR.isValidCNPJ(clienteInsertDTO.getDocumento()))
            errors.add(new FieldMessage("documento", "CNPJ inválido"));

        for (FieldMessage err : errors) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(err.getMessage())
                    .addPropertyNode(err.getFieldName())
                    .addConstraintViolation();
        }

        return errors.isEmpty();
    }

}
