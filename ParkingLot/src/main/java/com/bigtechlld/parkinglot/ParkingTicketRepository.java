package com.bigtechlld.parkinglot;

import com.bigtechlld.parkinglot.components.ParkingTicket;
import com.bigtechlld.parkinglot.exceptions.NoSuchTicketException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ParkingTicketRepository implements CrudRepository<ParkingTicket, String> {
    private final ConcurrentMap<String, ParkingTicket> ticketDb = new ConcurrentHashMap<>();

    private static ParkingTicketRepository instance;

    private ParkingTicketRepository(){}

    public static ParkingTicketRepository getInstance() {
        if(instance == null) {
            synchronized (ParkingTicketRepository.class) {
                if (instance == null) {
                    instance = new ParkingTicketRepository();
                }
            }
        }

        return instance;
    }

    @Override
    public synchronized ParkingTicket save(ParkingTicket parkingTicket) {
        return ticketDb.put(parkingTicket.getCarNumber(), parkingTicket);
    }

    @Override
    public synchronized ParkingTicket remove(String vehicleNumber) {
        ParkingTicket ticket = ticketDb.remove(vehicleNumber);
        if(ticket == null) {
            throw new NoSuchTicketException("Ticket with vehicle number " + vehicleNumber + " is not available");
        }

        return ticket;
    }


}
