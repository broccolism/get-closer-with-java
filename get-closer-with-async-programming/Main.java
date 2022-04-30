import static with.java8.Shop.*;

import java.util.concurrent.Future;

import with.java8.Shop;
import with.java8.Shops;

public class Main {
    public static void main(String[] args) {
        Shops.doSomething();
    }

    public static void firstMain(String[] args) {
        // AsyncBeforeJava8.doSomething();

        Shop shop = new Shop("broccoli shop");
        long start = System.nanoTime();

        Future<Double> futurePrice = shop.getPriceAsync("broccoli");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000); // 와 쩐다 자바 7부터 이런걸 제공함

        System.out.println("Invocation returned after " + invocationTime
                                                        + " msecs");

        doSomethingElse();
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}