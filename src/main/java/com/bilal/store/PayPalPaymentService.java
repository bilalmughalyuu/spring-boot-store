package com.bilal.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("paypal")
public class PayPalPaymentService implements PaymentService{

    @Value("${stripe.payPalApiUrl}")
    private String apiUrl;

    @Override
    public void processPayment(double amount) {
        System.out.print("PayPal amount " + amount);
        System.out.print(apiUrl);
    }
}
