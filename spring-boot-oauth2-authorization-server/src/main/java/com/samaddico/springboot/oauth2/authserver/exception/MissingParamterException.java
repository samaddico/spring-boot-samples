package com.samaddico.springboot.oauth2.authserver.exception;

public class MissingParamterException extends Exception {
    public MissingParamterException() {
    }

    public MissingParamterException(String message) {
        super(message);
    }

    public MissingParamterException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingParamterException(Throwable cause) {
        super(cause);
    }

    public MissingParamterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
