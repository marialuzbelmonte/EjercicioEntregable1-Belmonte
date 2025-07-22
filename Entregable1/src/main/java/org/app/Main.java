package org.app;

import org.app.Logic.Payment.*;
import org.app.Logic.Shipping.*;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos del pago
        System.out.println("Ingrese el monto a pagar:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el proveedor de pago (paypal o mercadopago):");
        String provider = scanner.nextLine().toLowerCase();

        // Datos del envío
        System.out.println("Ingrese el peso del paquete (en kg):");
        double weight = scanner.nextDouble();

        System.out.println("Ingrese ancho (en cm):");
        double width = scanner.nextDouble();
        System.out.println("Ingrese alto (en cm):");
        double height = scanner.nextDouble();
        System.out.println("Ingrese largo (en cm):");
        double length = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Ingrese origen:");
        String origin = scanner.nextLine();

        System.out.println("Ingrese destino:");
        String destination = scanner.nextLine();

        System.out.println("Ingrese el metodo de envio (air, truck, boat):");
        String shippingMethod = scanner.nextLine().toLowerCase();

        CompletableFuture<Double> shippingCostFuture = CompletableFuture.supplyAsync(() -> {
            Dimensions dimensions = new Dimensions(height, width, length);
            ShippingStrategy strategy;

            switch (shippingMethod) {
                case "air":
                    strategy = new AirShippingStrategy();
                    break;
                case "truck":
                    strategy = new TruckShippingStrategy();
                    break;
                case "boat":
                    strategy = new BoatShippingStrategy();
                    break;
                default:
                    throw new IllegalArgumentException("Metodo invalido");
            }

            ShippingRequest shippingRequest = new ShippingRequest(weight, dimensions, origin, destination);
            ShippingCalculator calculator = new ShippingCalculator(shippingRequest, strategy);
            double cost = calculator.calculateCost();
            System.out.println("Costo de envio: $" + cost);
            return cost;
        });

        CompletableFuture<Void> paymentFuture = shippingCostFuture.thenAcceptAsync(shippingCost -> {
            double totalAmount = amount + shippingCost;
            PaymentRequest paymentRequest = new PaymentRequest(totalAmount, provider);
            PaymentManager paymentManager = new PaymentManager();
            paymentManager.processPayment(paymentRequest);
        });

        paymentFuture.join();
        System.out.println("Pago y envio completados.");
    }
}
