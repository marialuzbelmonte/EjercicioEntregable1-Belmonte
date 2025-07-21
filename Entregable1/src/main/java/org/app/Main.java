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
        scanner.nextLine(); // limpiar buffer

        System.out.println("Seleccione el proveedor de pago (paypal o mercadopago):");
        String provider = scanner.nextLine().toLowerCase();

        // Datos del envío
        System.out.println("Ingrese el peso del paquete:");
        double weight = scanner.nextDouble();

        System.out.println("Ingrese ancho:");
        double width = scanner.nextDouble();
        System.out.println("Ingrese alto:");
        double height = scanner.nextDouble();
        System.out.println("Ingrese largo:");
        double length = scanner.nextDouble();

        scanner.nextLine(); // limpiar buffer

        System.out.println("Ingrese origen:");
        String origin = scanner.nextLine();

        System.out.println("Ingrese destino:");
        String destination = scanner.nextLine();

        System.out.println("Seleccione el método de envío (air, truck, boat):");
        String shippingMethod = scanner.nextLine().toLowerCase();

        // Armar futures en paralelo
        CompletableFuture<Void> paymentFuture = CompletableFuture.runAsync(() -> {
            PaymentRequest request = new PaymentRequest(amount);
            PaymentManager paymentManager = new PaymentManager();
            paymentManager.processPayment(request, provider);
        });

        CompletableFuture<Void> shippingFuture = CompletableFuture.runAsync(() -> {
            Dimensions dimensions = new Dimensions(width, height, length);
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
                    throw new IllegalArgumentException("Método de envío no válido");
            }

            ShippingCalculator calculator = new ShippingCalculator(weight, dimensions, origin, destination, strategy);
            double cost = calculator.calculateCost();
            System.out.println("Costo de envío: $" + cost);
        });

        // Esperar a que terminen ambos
        CompletableFuture<Void> combined = CompletableFuture.allOf(paymentFuture, shippingFuture);
        combined.join();

        System.out.println("Proceso completado.");
    }
}
