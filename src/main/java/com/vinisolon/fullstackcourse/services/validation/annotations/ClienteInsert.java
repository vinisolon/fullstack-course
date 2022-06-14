package com.vinisolon.fullstackcourse.services.validation.annotations;

import com.vinisolon.fullstackcourse.services.validation.validators.ClienteInsertValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {

    String message() default "Erro na validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
