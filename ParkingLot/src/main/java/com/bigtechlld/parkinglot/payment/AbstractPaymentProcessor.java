package com.bigtechlld.parkinglot.payment;

public abstract class AbstractPaymentProcessor {
    abstract boolean pay(double amount, PaymentStrategy strategy);
}
