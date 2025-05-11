package com.bigtechlld.parkinglot.components;

import com.bigtechlld.parkinglot.components.common.VehicleType;

import java.time.LocalDateTime;

public final class ParkingTicket {
    private final String carNumber;
    private final VehicleType vehicleType;
    private final int floorNumber;
    private final int parkingSpotNumber;
    private final LocalDateTime entryTimestamp = LocalDateTime.now();

    public ParkingTicket(String carNumber, VehicleType vehicleType, int floorNumber, int parkingSpotNumber) {
        this.carNumber = carNumber;
        this.vehicleType = vehicleType;
        this.floorNumber = floorNumber;
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public LocalDateTime getEntryTimestamp() {
        return entryTimestamp;
    }
}
