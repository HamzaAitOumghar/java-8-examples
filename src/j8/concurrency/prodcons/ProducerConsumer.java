package j8.concurrency.prodcons;

public class ProducerConsumer {

    private static int[] buffer;
    private static int count;
    private static Object key = new Object();

    static class Producer {
        void producer() {
            synchronized (key) {
                if(isFull(buffer)) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                key.notify();
            }
        }


    }

    private static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    private static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static class Consumer {
        void consume()  {
            synchronized (key) {
                if (isEmpty(buffer)) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--count] = 0;
                key.notify();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        buffer=new int[10];
        count=0;
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = ()->{
            for (int i = 0; i <50 ; i++) {
                producer.producer();
            }
            System.out.println("Done producing");
        };

        Runnable consumeTask = ()->{
            for (int i = 0; i <50 ; i++) {
                consumer.consume();
            }
            System.out.println("Done consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread produceThread = new Thread(produceTask);

        consumerThread.start();
        produceThread.start();

        consumerThread.join();
        produceThread.join();

        System.out.println("Data in the buffer "+ count);


    }

}
