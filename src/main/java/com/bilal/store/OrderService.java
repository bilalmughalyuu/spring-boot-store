package com.bilal.store;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {

    private PaymentService paymentService;

//    public OrderService() {
//
//    }

//    @Autowired
//    public OrderService(@Qualifier("paypal") PaymentService paymentService) {
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Order service post construct");
    }


    @PreDestroy
    public void cleanUp() {
        System.out.println("Order Service Pre Destroy");
    }

    public void placeOrder() {
        paymentService.processPayment(20);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
