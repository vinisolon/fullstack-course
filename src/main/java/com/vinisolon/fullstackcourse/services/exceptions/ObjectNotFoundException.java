package com.vinisolon.fullstackcourse.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2629894519176722739L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
