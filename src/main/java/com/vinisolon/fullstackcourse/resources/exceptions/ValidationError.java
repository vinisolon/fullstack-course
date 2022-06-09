package com.vinisolon.fullstackcourse.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends StandartError {

    private static final long serialVersionUID = -6678850655089423760L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, String path, Instant time) {
        super(status, msg, path, time);
    }

    public void setEachError(String field, String message) {
        this.errors.add(new FieldMessage(field, message));
    }

}
