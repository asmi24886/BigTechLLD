package com.bigtechlld.parkinglot.components;

import com.bigtechlld.parkinglot.ParkingTicketRepository;
import com.bigtechlld.parkinglot.components.common.VehicleType;
import com.bigtechlld.parkinglot.components.parkingspot.ParkingSpot;
import com.bigtechlld.parkinglot.events.Event;
import com.bigtechlld.parkinglot.exceptions.NoSuchParkingSpotException;
import com.bigtechlld.parkinglot.exceptions.ParkingFullException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingEntrance {
    private AtomicBoolean isOpen = new AtomicBoolean(false);
    private final int gateNumber;
    private final EntranceManager manager = new EntranceManager();

    private class EntranceManager {
        private BlockingQueue<Event> entryQueue = new LinkedBlockingQueue<>();
        private BlockingQueue<Event> exitQueue = new LinkedBlockingQueue<>();

        public BlockingQueue<Event> getEntryQueue() {
            return entryQueue;
        }

        public void setEntryQueue(BlockingQueue<Event> entryQueue) {
            this.entryQueue = entryQueue;
        }

        public BlockingQueue<Event> getExitQueue() {
            return exitQueue;
        }

        public void setExitQueue(BlockingQueue<Event> exitQueue) {
            this.exitQueue = exitQueue;
        }

        public boolean requestEntry(String carNumber, VehicleType type) {
            try {
                return entryQueue.add(new Event(carNumber, type));
            }
            catch (IllegalStateException e) {
                System.out.println("Entry denied at this point due to long queue. Please try after some time");
                return  false;
            }
        }

        public boolean requestExit(String carNumber, VehicleType type) {
            try {
                exitQueue.add(new Event(carNumber, type));
                return true;
            }
            catch (IllegalStateException e) {
                System.out.println("Exit denied at this point due to long queue. Please try after some time");
                return false;
            }
        }

        public ParkingTicket processNextEntry() {
            Event e = entryQueue.peek();
            if(e == null)
                return null;

            ParkingLot parking = ParkingLot.getInstance();
            ParkingSpot spot;
            for(ParkingFloor floor : parking.getFloors()) {
                spot = floor.allotSpot(gateNumber, e.getVehicleType());
                if(spot == null) continue;
                entryQueue.poll();
                return new ParkingTicket(e.getVehicleNumber(), e.getVehicleType(), spot.getFloorNumber(), spot.getSpotNumber() );
            }

            throw new ParkingFullException("The parking lot is full. Please wait for some time in queue. Sorry for the inconvenience.");
        }

        public void processNextExit() {
            Event e = exitQueue.peek();
            if(e == null)
                return;

            ParkingTicket ticket = ParkingTicketRepository.getInstance().remove(e.getVehicleNumber());
            ParkingLot parking = ParkingLot.getInstance();

            for(ParkingFloor floor : parking.getFloors()) {
                if(floor.getFloorNumber() != ticket.getFloorNumber()) {
                    continue;
                }

                try {
                    floor.freeSpot(ticket.getParkingSpotNumber());
                } catch (NoSuchParkingSpotException ex) {
                    return;
                }
                exitQueue.poll();
            }
        }

    }

    public ParkingEntrance(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void open() {
        isOpen.set(true);
        try (ExecutorService service = Executors.newFixedThreadPool(2)) {
            service.submit(() -> { while (isOpen.get()) { manager.processNextEntry(); } });
            service.submit(() -> { while (isOpen.get()) { manager.processNextExit(); } });
        } finally {
            System.out.println("Entrance gate entry/exit process completed");
        }
    }

    public void close() {
        isOpen.set(false);
    }
}
