package org.app.Logic.Payment;

public class PaypalPaymentProcessor extends PaymentProcessor{
    public PaypalPaymentProcessor(PaymentGateway gateway) {
        super(gateway);
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[PayPal Processor] Procesando pago...");
        return gateway.authorize(amount) && gateway.capture(amount);
    }

    @Override
    public boolean refundPayment(double amount) {
        System.out.println("[PayPal Processor] Reembolsando $" + amount);
        return true;
    }

}
