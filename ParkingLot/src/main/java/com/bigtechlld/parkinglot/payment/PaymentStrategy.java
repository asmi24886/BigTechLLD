package com.bigtechlld.parkinglot.payment;

public interface PaymentStrategy {
    boolean processPayment(double amount);
}
