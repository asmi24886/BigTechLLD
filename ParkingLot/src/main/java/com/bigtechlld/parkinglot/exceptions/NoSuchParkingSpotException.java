package com.bigtechlld.parkinglot.exceptions;

public class NoSuchParkingSpotException extends RuntimeException{
    public NoSuchParkingSpotException(String message) {
        super(message);
    }
}
