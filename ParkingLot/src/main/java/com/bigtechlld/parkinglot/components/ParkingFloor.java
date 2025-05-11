package com.bigtechlld.parkinglot.components;

import com.bigtechlld.parkinglot.exceptions.NoSuchParkingSpotException;
import com.bigtechlld.parkinglot.exceptions.NoSuchVehicleTypeException;
import com.bigtechlld.parkinglot.components.config.ParkingLotConfigurations;
import com.bigtechlld.parkinglot.components.common.VehicleType;
import com.bigtechlld.parkinglot.components.common.ObservableComponent;
import com.bigtechlld.parkinglot.components.common.ObserverComponent;
import com.bigtechlld.parkinglot.components.parkingspot.FourWheelerParkingSpot;
import com.bigtechlld.parkinglot.components.parkingspot.ParkingSpot;
import com.bigtechlld.parkinglot.components.parkingspot.TwoWheelerParkingSpot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingFloor implements ObservableComponent {
    private final int floorNumber;
    private final Map<VehicleType, ArrayList<ParkingSpot>> parkingSpots;
    private final Set<ObserverComponent> observers;

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        parkingSpots = new HashMap<>();
        observers = new HashSet<>();
        init();
    }

    private void init() {
        parkingSpots.put(VehicleType.FOUR_WHEELER, new ArrayList<>());
        parkingSpots.put(VehicleType.TWO_WHEELER, new ArrayList<>());
        IntStream.range(0, ParkingLotConfigurations.FOUR_WHEELER_SPACE_PER_FLOOR).forEach(i -> {
            parkingSpots.get(VehicleType.FOUR_WHEELER).add(
                new FourWheelerParkingSpot(i, floorNumber)
            );

        });

        IntStream.range(parkingSpots.size(), ParkingLotConfigurations.PARKING_PER_FLOOR).forEach(i -> {
            parkingSpots.get(VehicleType.FOUR_WHEELER).add(
                    new TwoWheelerParkingSpot(i, floorNumber)
            );
        });
    }

    List<ParkingSpot> getSpots(VehicleType type, boolean isFree) {
        if(type == null) {
            return parkingSpots.values().stream()
                    .flatMap(it -> it.stream().filter(spot -> spot.isFree() == isFree))
                    .collect(Collectors.toList());
        }

        List<ParkingSpot> allSpots = parkingSpots.get(type);
        if(allSpots == null) {
            throw new NoSuchVehicleTypeException("No such vehicle type could be found");
        }

        return allSpots.stream().filter(it -> it.isFree() == isFree).collect(Collectors.toList());
    }

    public ParkingSpot allotSpot(int entranceNumber, VehicleType vehicleType) {
        List<ParkingSpot> freeSpots = getSpots(vehicleType, true);

        synchronized (this) {
            ParkingSpot spot = getNearest(freeSpots, entranceNumber);
            if(spot != null)
                spot.setFree(false);

            return spot;
        }
    }

    public ParkingSpot freeSpot(int spotNumber) {
        List<ParkingSpot> occupiedSpots = getSpots(null, false);

        ParkingSpot spotToFind = new ParkingSpot();
        spotToFind.setFloorNumber(floorNumber);
        spotToFind.setSpotNumber(spotNumber);

        synchronized (this) {
            int index  = Collections.binarySearch(occupiedSpots, spotToFind);
            if(index < 0) {
                throw new NoSuchParkingSpotException("No such parking spot could be found");
            }
            ParkingSpot spot = occupiedSpots.get(index);
            spot.setFree(true);
            return spot;
        }
    }

    private ParkingSpot getNearest(List<ParkingSpot> spots, int entranceNumber) {
        if(spots.isEmpty()) return null;

        ParkingSpot spotToFind = new ParkingSpot();
        spotToFind.setFloorNumber(floorNumber);
        spotToFind.setSpotNumber(entranceNumber);

        int index  = Collections.binarySearch(spots, spotToFind);
        if(index < 0) {
            return spots.get(-1*index - 1);
        }
        return spots.get(index);
    }

    @Override
    public void registerObserver(ObserverComponent o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(it -> it.onChange(this));
    }
}