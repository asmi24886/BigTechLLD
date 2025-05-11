package com.bigtechlld.parkinglot.payment;

public class CCPaymentStrategy implements PaymentStrategy {
    private String ccNumber;
    private String expiry;
    private String cvv;

    public CCPaymentStrategy(String ccNumber, String expiry, String cvv) {
        this.ccNumber = ccNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        return false;
    }
}
