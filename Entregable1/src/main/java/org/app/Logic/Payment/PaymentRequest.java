package org.app.Logic.Payment;

public class PaymentRequest {
    private double amount;
    private String provider;

    public PaymentRequest(double amount, String provider) {
        this.amount = amount;
        this.provider = provider;
    }

    public double getAmount() {
        return amount;
    }

    public String getProvider() {
        return provider;
    }
}
