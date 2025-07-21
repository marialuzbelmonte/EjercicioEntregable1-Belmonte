package org.app.Logic.Shipping;

public interface ShippingStrategy {
    double calculateCost(ShippingRequest shippingRequest);
}
