package j8.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SumAction extends RecursiveAction {

    private static final int SEQUENTIAL_THRESHOLD = 5;

    private List<Long> data;

    public SumAction(List<Long> data) {
        this.data = data;
    }

    @Override
    protected void compute() {

        if (data.size() < SEQUENTIAL_THRESHOLD) {
            long sum = computeSumDirectly();
            System.out.format("Sum of %s: %d\n", data.toString(), sum);
        } else {
            int mid = data.size() / 2;
            SumAction firstSubtask = new SumAction(data.subList(0, mid));
            SumAction secondSubtask = new SumAction(data.subList(mid, data.size()));
            invokeAll(firstSubtask, secondSubtask);
        }
    }

    private long computeSumDirectly() {
        long sum = 0;
        for (Long l : data) {
            sum += l;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Long> data = new Random().longs(100, 0, 100).boxed().collect(Collectors.toList());
        ForkJoinPool pool = new ForkJoinPool();
        SumAction task = new SumAction(data);
        pool.invoke(task);

    }

}
