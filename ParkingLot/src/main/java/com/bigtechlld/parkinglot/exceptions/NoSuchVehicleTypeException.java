package com.bigtechlld.parkinglot.exceptions;

public class NoSuchVehicleTypeException extends RuntimeException{
    public NoSuchVehicleTypeException(String message) {
        super(message);
    }
}
