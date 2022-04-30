import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncBeforeJava8 {
    public static void doSomething() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> {
            try {
                return doSomeLongComputation();
            } catch (Exception e) {
                return 7.0;
            }
        });

        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.printf("Result: %f\n", result);
        } catch (ExecutionException e) {
            System.out.println("Something went wrong while calculation...");
        } catch (InterruptedException ie) {
            System.out.println("Interrupted!!!");
        } catch (TimeoutException te) {
            System.out.println("Timeout!!!");
        }

        System.out.println("Bye.");
    }

    private static Double doSomeLongComputation() throws InterruptedException {
        Thread.sleep(800);
        return 3.5;
    }

    private static void doSomethingElse() {
        System.out.println("Haha! I'm doing something else now...");
    }
}
