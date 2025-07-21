package org.app.Logic.Payment;

public class MercadoPagoPaymentProcessor extends PaymentProcessor{
    public MercadoPagoPaymentProcessor(PaymentGateway gateway) {
        super(gateway);
    }

    @Override
    public boolean processPayment(double amount) {

        //VEEEEERRR
        System.out.println("[MercadoPago Processor] Procesando pago...");
        return gateway.authorize(amount) && gateway.capture(amount);
    }

    @Override
    public boolean refundPayment(double amount) {

        //VEEEEERRR
        System.out.println("[MercadoPago Processor] Reembolsando $" + amount);
        return true;
    }

}
