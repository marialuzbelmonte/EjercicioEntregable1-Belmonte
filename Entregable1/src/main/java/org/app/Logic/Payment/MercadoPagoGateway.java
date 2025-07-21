package org.app.Logic.Payment;

public class MercadoPagoGateway implements PaymentGateway{
    @Override
    public boolean authorize(double amount) {

        //VEEEEERRRRR
        System.out.println("[MercadoPago Gateway] Autorizando pago de $" + amount);
        return true;
    }

    @Override
    public boolean capture(double amount) {

        //VEEEEEERRRR
        System.out.println("[MercadoPago Gateway] Capturando pago de $" + amount);
        return true;
    }
}
