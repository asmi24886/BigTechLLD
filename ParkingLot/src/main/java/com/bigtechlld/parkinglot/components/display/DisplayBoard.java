package com.bigtechlld.parkinglot.components.display;

import com.bigtechlld.parkinglot.components.ParkingLot;
import com.bigtechlld.parkinglot.components.common.ObservableComponent;
import com.bigtechlld.parkinglot.components.common.ObserverComponent;

public class DisplayBoard implements ObserverComponent {

    private static DisplayBoard instance;

    private DisplayBoard(){}

    public static DisplayBoard getInstance() {
        if(instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new DisplayBoard();
                }
            }
        }

        return instance;
    }

    @Override
    public void onChange(ObservableComponent o) {
        System.out.println(o);
        System.out.println(ParkingLot.getInstance());
        //Further implementation on follow up question
        System.out.println("==========================================");
    }
}
