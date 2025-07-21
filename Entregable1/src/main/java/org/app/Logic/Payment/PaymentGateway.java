package org.app.Logic.Payment;

public interface PaymentGateway {
    boolean authorize(double amount);
    boolean capture(double amount);
}
