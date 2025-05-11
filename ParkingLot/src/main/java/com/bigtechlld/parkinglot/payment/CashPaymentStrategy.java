package com.bigtechlld.parkinglot.payment;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}
