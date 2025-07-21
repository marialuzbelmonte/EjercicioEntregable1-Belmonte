package org.app.Logic.Shipping;

public class BoatShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(ShippingRequest shippingRequest) {
        double baseRate = 3.0;
        double volume = shippingRequest.getDimensions().getVolume();
        return baseRate + 0.01 * volume + 0.5 * shippingRequest.getWeight();
    }
}

