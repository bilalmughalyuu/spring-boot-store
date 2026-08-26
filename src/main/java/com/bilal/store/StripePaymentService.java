package com.bilal.store;

public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.print("Stripe amount " + amount);
    }
}
