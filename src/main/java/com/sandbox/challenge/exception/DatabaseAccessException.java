package com.sandbox.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Employee creation failed")
public class DatabaseAccessException extends RuntimeException {

    private HttpStatus status;

    private String message;

    public DatabaseAccessException(String message){
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }
}