package j8.concurrency.prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProducerConsumerCollection {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(50);

        class Consumer implements Callable<String>{
            @Override
            public String call() throws Exception {
                int count =0;
                while (count++<50){
                    queue.take();
                }

                return "Consumed " + (count-1);
            }
        }


        class Producer implements Callable<String>{
            @Override
            public String call() throws Exception {
                int count =0;
                while (count++<50){
                    queue.put(Integer.toString(count));
                }
                return "Produced " + (count-1);
            }
        }

        List<Callable<String>> producersAndConsumers = new ArrayList<>();

        for (int i = 0; i <2 ; i++) {
           producersAndConsumers.add(new Producer());
        }

        for (int i = 0; i <2 ; i++) {
            producersAndConsumers.add(new Consumer());
        }

        System.out.println("Producers and Consumers launched");

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

            futures.forEach(f->{
                try {
                    System.out.println(f.get());
                } catch (Exception e) {
                    System.out.println("Exception "+e.getMessage());
                }
            });

        } catch (InterruptedException e) {
            executorService.shutdown();
        }


    }
}
