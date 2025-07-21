package org.app.Logic.Shipping;

public class AirShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(ShippingRequest shippingRequest) {
        double baseRate = 5.0;
        double volume = shippingRequest.getDimensions().getVolume();
        return baseRate * shippingRequest.getWeight() + 0.02 * volume;
    }
}
