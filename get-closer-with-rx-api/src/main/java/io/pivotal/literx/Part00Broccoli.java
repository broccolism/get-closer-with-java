package io.pivotal.literx;

import java.util.Arrays;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

// https://medium.com/swlh/understanding-reactors-flatmap-operator-a6a7e62d3e95
public class Part00Broccoli {
    public static void main(String[] args) {
        Flux<Integer> f = Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
        f.flatMap(a -> Mono.just(a).subscribeOn(Schedulers.parallel()))
            .doOnNext(
                a ->
                    System.out.println(
                        "Received: " + a + " on thread: " + Thread.currentThread().getName()))
            .flatMap(
                a -> {
                    System.out.println(
                        "Received in flatMap: " + a + " on thread: " + Thread.currentThread().getName());
                    a++;
                    return Mono.just(a).subscribeOn(Schedulers.elastic());
                })
            .subscribe(
                a ->
                    System.out.println(
                        "Received (in the subscriber): "
                            + a
                            + " on thread: "
                            + Thread.currentThread().getName()));
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
