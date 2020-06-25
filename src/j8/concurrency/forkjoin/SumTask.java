package j8.concurrency.forkjoin;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class SumTask extends RecursiveTask<Long> {

    private static final int SEQUENTIAL_THRESHOLD = 5;
    private List<Long> data;

    public SumTask(List<Long> data) {
        this.data = data;
    }

    @Override
    protected Long compute() {

        if (data.size() < SEQUENTIAL_THRESHOLD) {
            long sum = calculeSum(data);
            System.out.format("Sum of %s: %d\n", data.toString(), sum);
            return sum;
        } else {
            int middle = data.size() / 2;
            SumTask sumTask1 = new SumTask(data.subList(0, middle));
            SumTask sumTask2 = new SumTask(data.subList(middle + 1, data.size()));
            sumTask1.fork();
            return sumTask2.compute() +sumTask1.join();
        }
    }

    private long calculeSum(List<Long> data) {
        return data.stream().reduce(0l, Long::sum);
    }

    public static void main(String[] args) {
        List<Long> data = new Random().longs(100, 0, 100).boxed().collect(Collectors.toList());
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        System.out.println("Sum: " + pool.invoke(task));

    }
}
