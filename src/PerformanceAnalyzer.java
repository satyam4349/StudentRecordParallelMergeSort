import java.util.Arrays;

public class PerformanceAnalyzer {

    public static class Result {
        public final long sequentialNanos;
        public final long parallelNanos;
        public final double speedup;

        public Result(long sequentialNanos, long parallelNanos) {
            this.sequentialNanos = sequentialNanos;
            this.parallelNanos = parallelNanos;
            this.speedup = parallelNanos == 0
                    ? Double.POSITIVE_INFINITY
                    : (double) sequentialNanos / parallelNanos;
        }

        public double sequentialMs() {
            return sequentialNanos / 1_000_000.0;
        }

        public double parallelMs() {
            return parallelNanos / 1_000_000.0;
        }
    }

    private PerformanceAnalyzer() {}

    public static Result compare(Student[] source, int threads, int threshold) {
        Student[] sequential = Arrays.copyOf(source, source.length);
        Student[] parallel = Arrays.copyOf(source, source.length);

        // Small warm-up to reduce first-run JVM/JIT effects.
        Student[] warmup = Arrays.copyOf(source, Math.min(source.length, 5000));
        MergeSort.sort(warmup);

        long start = System.nanoTime();
        MergeSort.sort(sequential);
        long sequentialTime = System.nanoTime() - start;

        ParallelMergeSort sorter = new ParallelMergeSort(threads, threshold);
        start = System.nanoTime();
        sorter.sort(parallel);
        long parallelTime = System.nanoTime() - start;
        sorter.shutdown();

        if (!isSorted(sequential) || !isSorted(parallel)) {
            throw new IllegalStateException("Sorting verification failed.");
        }

        return new Result(sequentialTime, parallelTime);
    }

    public static boolean isSorted(Student[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].getMarks() < array[i].getMarks()) {
                return false;
            }
        }
        return true;
    }
}
