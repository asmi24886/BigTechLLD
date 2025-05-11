package com.bigtechlld.parkinglot.components.parkingspot;

import com.bigtechlld.parkinglot.components.common.VehicleType;

import java.util.Objects;

public class ParkingSpot implements Comparable<ParkingSpot>{
    private int spotNumber;
    private int floorNumber;
    private boolean isFree;
    private VehicleType allotedVehicleTYpe;

    public ParkingSpot(){}

    public ParkingSpot(int spotNumber, int floorNumber, boolean isFree, VehicleType allotedVehicleTYpe) {
        this.spotNumber = spotNumber;
        this.floorNumber = floorNumber;
        this.isFree = isFree;
        this.allotedVehicleTYpe = allotedVehicleTYpe;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public VehicleType getAllotedVehicleTYpe() {
        return allotedVehicleTYpe;
    }

    public void setAllotedVehicleTYpe(VehicleType allotedVehicleTYpe) {
        this.allotedVehicleTYpe = allotedVehicleTYpe;
    }

    @Override
    public int compareTo(ParkingSpot o) {
        return this.spotNumber - o.spotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return spotNumber == that.spotNumber && floorNumber == that.floorNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotNumber, floorNumber);
    }
}
