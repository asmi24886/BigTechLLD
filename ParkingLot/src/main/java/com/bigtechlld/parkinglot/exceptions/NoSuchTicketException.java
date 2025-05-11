package com.bigtechlld.parkinglot.exceptions;

public class NoSuchTicketException extends RuntimeException{
    public NoSuchTicketException(String message) {
        super(message);
    }
}
