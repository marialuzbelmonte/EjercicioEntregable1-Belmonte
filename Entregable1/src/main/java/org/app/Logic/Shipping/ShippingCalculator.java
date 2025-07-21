package org.app.Logic.Shipping;

public class ShippingCalculator {
    private ShippingRequest shippingRequest;
    private ShippingStrategy strategy;

    public ShippingCalculator(ShippingRequest shippingRequest, ShippingStrategy strategy) {
        this.shippingRequest = shippingRequest;
        this.strategy = strategy;
    }

    public double calculateCost() {
        return strategy.calculateCost(shippingRequest);
    }

}
