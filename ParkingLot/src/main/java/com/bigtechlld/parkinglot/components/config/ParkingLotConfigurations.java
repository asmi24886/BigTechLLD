package com.bigtechlld.parkinglot.components.config;

public class ParkingLotConfigurations {

    public static int TOTAL_FLOORS = 3;
    public static int MAX_ENTRANCES = 5;
    public static int PARKING_PER_FLOOR = 20;
    public static int TWO_WHEELER_PER_FLOOR = (int) PARKING_PER_FLOOR/4;
    public static int FOUR_WHEELER_SPACE_PER_FLOOR = PARKING_PER_FLOOR - TWO_WHEELER_PER_FLOOR;
}
