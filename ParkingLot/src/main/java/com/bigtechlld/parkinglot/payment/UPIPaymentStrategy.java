package com.bigtechlld.parkinglot.payment;

public class UPIPaymentStrategy implements PaymentStrategy {

    private String upiId;

    public UPIPaymentStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}
