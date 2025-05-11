package com.bigtechlld.parkinglot.payment;

public class ParkingLotPaymentManager extends AbstractPaymentProcessor implements BasicPaymentModeReceiver {

    public final boolean payWithCash(double amount) {
        return pay(amount, new CashPaymentStrategy());
    }

    public final boolean payWithCC(double amount, String ccNumber, String expiry, String cvv) {
        return pay(amount, new CCPaymentStrategy(ccNumber, expiry, cvv));
    }

    public final boolean payWithUPI(double amount, String upiId) {
        return pay(amount, new UPIPaymentStrategy(upiId));
    }

    boolean pay(double amount, PaymentStrategy strategy) {
        return strategy.processPayment(amount);
    }
}
