package com.bigtechlld.parkinglot.payment;

public interface BasicPaymentModeReceiver {

    boolean payWithCash(double amount);

    boolean payWithCC(double amount, String ccNumber, String expiry, String cvv);

    boolean payWithUPI(double amount, String upiId);
}
