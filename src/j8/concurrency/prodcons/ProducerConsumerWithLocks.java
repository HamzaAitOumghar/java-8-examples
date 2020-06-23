package j8.concurrency.prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> buffer = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition isEmpty = lock.newCondition();
        Condition isFull = lock.newCondition();

        class Consumer implements Callable<String> {
            @Override
            public String call() throws Exception {
                int count = 0;
                while (count++ < 50) {
                    try {
                        lock.lock();
                        while (isEmpty(buffer)) {
                            isEmpty.await();
                        }
                        buffer.remove(buffer.size() - 1);
                        isFull.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return "Consumed " + (count - 1);
            }
        }

        class Producer implements Callable<String> {

            @Override
            public String call() throws Exception {
                int count = 0;
                while (count++ < 50) {
                    try {
                        lock.lock();
                        while (isFull(buffer)) {
                            isFull.await();
                        }
                        buffer.add(1);
                        isEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return "Produced " + (count - 1);
            }

        }
        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            producers.add(new Producer());
        }
        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            consumers.add(new Consumer());
        }
        System.out.println("Producers and Consumers launched");

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        producersAndConsumers.addAll(producers);
        producersAndConsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try{
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

            futures.forEach(f->{
                try {
                    System.out.println(f.get());
                } catch (Exception e) {
                    System.out.println("Exception: "+e.getMessage());
                }
            });

        } finally {
            executorService.shutdown();
            System.out.println("Executor service shut down");
        }


    }

    private static boolean isFull(List<Integer> buffer) {
        return buffer.size() == 50;
    }

    private static boolean isEmpty(List<Integer> buffer) {
        return buffer.isEmpty();
    }

}
