package com.Ilker.Petify.exception;


public class BreedAlreadyExistException extends RuntimeException {

    public BreedAlreadyExistException(String message) {
        super(message);
    }
}