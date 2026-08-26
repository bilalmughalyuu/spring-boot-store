package com.bilal.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("stripe")
public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.print("Stripe amount " + amount);
    }
}
