package with.java8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static void doSomethingElse() {
        System.out.println("Haha! I'm doing something else now...");
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();

        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        return (new Random()).nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
