package com.bilal.store.learning;

import org.springframework.beans.factory.annotation.Value;

//@Primary
//@Service("stripe")
public class StripePaymentService implements PaymentService {

    @Value("${stripe.stripeApiUrl}")
    private String apiUrl;

    @Override
    public void processPayment(double amount) {
        System.out.print("Stripe amount " + amount);
        System.out.print(apiUrl);
    }
}
