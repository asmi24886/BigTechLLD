package com.bigtechlld.parkinglot.components;

import com.bigtechlld.parkinglot.components.config.ParkingLotConfigurations;
import com.bigtechlld.parkinglot.components.display.DisplayBoard;
import com.bigtechlld.parkinglot.exceptions.NotAllowedException;

import java.util.ArrayList;
import java.util.List;

public final class ParkingLot {
    private final List<ParkingEntrance> entrances = new ArrayList<>();
    private final List<ParkingFloor> floors = new ArrayList<>();

    private static ParkingLot instance;

    private ParkingLot(){}

    public static ParkingLot getInstance() {
        if(instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }

        return instance;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public List<ParkingEntrance> getEntrances() {
        return entrances;
    }

    public DisplayBoard getDisplayBoard() {
        return DisplayBoard.getInstance();
    }

    public synchronized ParkingLot addFloor() {
        if(floors.size() == ParkingLotConfigurations.TOTAL_FLOORS) {
            throw new NotAllowedException("You are not allowed to create more than " + ParkingLotConfigurations.TOTAL_FLOORS + " floors" );
        }
        ParkingFloor floor = new ParkingFloor(floors.size());
        floor.registerObserver(DisplayBoard.getInstance());
        floors.add(floor);
        return this;
    }

    public synchronized ParkingLot addEntrance() {
        if(entrances.size() == ParkingLotConfigurations.MAX_ENTRANCES) {
            throw new NotAllowedException("You are not allowed to create more than " + ParkingLotConfigurations.MAX_ENTRANCES + " entrances" );
        }
        entrances.add(new ParkingEntrance(entrances.size()));
        return this;
    }
}
