package com.bigtechlld.parkinglot.components.parkingspot;

import com.bigtechlld.parkinglot.components.common.VehicleType;

public class FourWheelerParkingSpot  extends ParkingSpot {
    public FourWheelerParkingSpot(){}
    public FourWheelerParkingSpot(int spotNumber, int floorNumber) {
        super(spotNumber, floorNumber, true, VehicleType.FOUR_WHEELER);
    }
}
