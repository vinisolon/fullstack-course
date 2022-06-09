package com.vinisolon.fullstackcourse.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = -2461930083119789042L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
