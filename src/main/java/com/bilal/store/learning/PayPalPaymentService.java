package com.bilal.store.learning;

import org.springframework.beans.factory.annotation.Value;

//@Service("paypal")
public class PayPalPaymentService implements PaymentService{

    @Value("${stripe.payPalApiUrl}")
    private String apiUrl;

    @Override
    public void processPayment(double amount) {
        System.out.print("PayPal amount " + amount);
        System.out.print(apiUrl);
    }
}
