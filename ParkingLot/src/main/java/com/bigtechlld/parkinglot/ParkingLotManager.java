package com.bigtechlld.parkinglot;

import com.bigtechlld.parkinglot.components.ParkingLot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingLotManagementExecutor {

    public static void start() {
        if(ParkingLot.getInstance().getEntrances().isEmpty())
            return;

        ExecutorService entryExecutor = Executors.newFixedThreadPool(ParkingLot.getInstance().getEntrances().size());

        ParkingLot.getInstance().getEntrances().forEach(

        );
    }
}
