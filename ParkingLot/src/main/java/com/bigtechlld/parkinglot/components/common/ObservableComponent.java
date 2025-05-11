package com.bigtechlld.parkinglot.components.common;

public interface ObservableComponent{
    void registerObserver(ObserverComponent o);
    void notifyObservers();
}
