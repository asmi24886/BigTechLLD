package com.bigtechlld.parkinglot.events;

import com.bigtechlld.parkinglot.components.common.VehicleType;

import java.time.LocalDateTime;

public class Event {

    private String vehicleNumber;
    private VehicleType carType;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Event(String carNumber, VehicleType carType) {
        this.vehicleNumber = carNumber;
        this.carType = carType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehichleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return carType;
    }

    public void setCarType(VehicleType carType) {
        this.carType = carType;
    }

}
