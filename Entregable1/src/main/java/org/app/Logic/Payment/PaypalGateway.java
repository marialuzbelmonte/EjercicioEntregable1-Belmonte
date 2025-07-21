package org.app.Logic.Payment;

public class PaypalGateway implements PaymentGateway{
    @Override
    public boolean authorize(double amount) {

        //VEERRRRRR
        System.out.println("[PayPal Gateway] Autorizando pago de $" + amount);
        return true;
    }

    @Override
    public boolean capture(double amount) {

        //VERRRRRRR
        System.out.println("[PayPal Gateway] Capturando pago de $" + amount);
        return true;
    }
}
