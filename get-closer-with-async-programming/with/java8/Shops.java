package with.java8;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class Shops {
    public static List<Shop> shops = Arrays.asList(
        new Shop("Broccoli"),
        new Shop("Carrot"),
        new Shop("Potato"),
        new Shop("고구마"),
        new Shop("인삼밭에 난 고구마"));

    private static final Executor executor = Executors.newFixedThreadPool(
        Math.min(shops.size(), 100),
        new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        }
    );

    public static void doSomething() {
        long start = System.nanoTime();
        System.out.println(findPrices("broccoli"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<String> findPrices(String product) {
        // return shops.stream()
        //     .map(shop -> String.format("In %s: price is %.2f",
        //         shop.getName(), shop.getPrice(product)))
        //     .collect(Collectors.toList());
        // return shops.parallelStream()
        //     .map(shop -> String.format("In %s: price is %.2f",
        //         shop.getName(), shop.getPrice(product)))
        //     .collect(Collectors.toList());


        // List<CompletableFuture<String>> priceFutures = shops.stream()
        //     .map(shop -> CompletableFuture.supplyAsync(
        //         () -> String.format("In %s: price is %.2f",
        //             shop.getName(), shop.getPrice(product))
        //     ))
        //     .collect(toList());
        //
        // return priceFutures.stream()
        //     .map(CompletableFuture::join)
        //     .collect(toList());


        // return shops.stream()
        //     .map(shop -> shop.getPrice(product))
        //     .map(Quote::parse)
        //     .map(Discount::applyDiscount)
        //     .collect(toList());

        List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .collect(toList());

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(toList());
    }
}
