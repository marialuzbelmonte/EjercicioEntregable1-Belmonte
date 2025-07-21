package org.app.Logic.Shipping;

public class TruckShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(ShippingRequest shippingRequest) {
        double baseRate = 2.5;
        boolean isLocal = shippingRequest.getOrigin().equalsIgnoreCase(shippingRequest.getDestination());
        double distanceFactor = isLocal ? 1.0 : 1.5;
        return baseRate * shippingRequest.getWeight() * distanceFactor;
    }
}

