package com.doodle.exceptions;

public class ResultNotFoundException extends RuntimeException {
    public ResultNotFoundException(String message){
        super(message);
    }
}
