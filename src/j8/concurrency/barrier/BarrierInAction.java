package j8.concurrency.barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BarrierInAction {

    public static void main(String[] args) {

        class Friend implements Callable<String> {

            private CyclicBarrier barrier;

            public Friend(CyclicBarrier barrier) {
                this.barrier = barrier;
            }

            @Override
            public String call() throws Exception {
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(20) * 100 + 100);
                    System.out.println("I just arrived,waiting for the others...");
                    barrier.await();
                    System.out.println("Let's gooooooooooo ......... ! ");
                    return "ok";
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                return "nok";
            }
        }


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        List<Future<String>> futures = new ArrayList<>();

        try {
            for (int i = 0; i < 4; i++) {
                Friend friend = new Friend(cyclicBarrier);
                futures.add(executorService.submit(friend));
            }
            futures.forEach(f -> {
                try {
                    f.get(200, TimeUnit.SECONDS);
                }
                catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
                catch (TimeoutException e) {
                    f.cancel(true);
                }

            });
        } finally {
            executorService.shutdown();
        }
    }
}
