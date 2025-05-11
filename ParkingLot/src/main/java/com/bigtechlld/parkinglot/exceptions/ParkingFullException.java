package com.bigtechlld.parkinglot.exceptions;

public class ParkingFullException extends RuntimeException{
    public ParkingFullException(String message) {
        super(message);
    }
}
