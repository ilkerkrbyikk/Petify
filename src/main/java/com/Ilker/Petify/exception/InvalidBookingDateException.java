package com.Ilker.Petify.exception;

public class InvalidBookingDateException extends RuntimeException {
    public InvalidBookingDateException(String s) {
        super(s);
    }
}
