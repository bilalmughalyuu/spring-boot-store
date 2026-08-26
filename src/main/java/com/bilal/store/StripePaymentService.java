package com.bilal.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
