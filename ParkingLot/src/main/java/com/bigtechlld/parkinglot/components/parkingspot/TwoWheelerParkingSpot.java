package com.bigtechlld.parkinglot.components.parkingspot;

import com.bigtechlld.parkinglot.components.common.VehicleType;

public class TwoWheelerParkingSpot extends ParkingSpot {
    public TwoWheelerParkingSpot(){}
    public TwoWheelerParkingSpot(int spotNumber, int floorNumber) {
        super(spotNumber, floorNumber, true, VehicleType.TWO_WHEELER);
    }
}
