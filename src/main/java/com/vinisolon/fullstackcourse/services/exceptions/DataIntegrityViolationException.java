package com.vinisolon.fullstackcourse.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

    private static final long serialVersionUID = -2461930083119789042L;

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }

    public DataIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
