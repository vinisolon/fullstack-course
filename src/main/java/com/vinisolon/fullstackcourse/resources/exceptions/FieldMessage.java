package com.vinisolon.fullstackcourse.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 8905034265492584268L;

    private String fieldName;
    private String message;

}
