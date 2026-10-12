import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    private final ForkJoinPool pool;
    private final int threshold;

    public ParallelMergeSort(int parallelism, int threshold) {
        this.pool = new ForkJoinPool(Math.max(1, parallelism));
        this.threshold = Math.max(32, threshold);
    }

    public void sort(Student[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        Student[] temp = new Student[array.length];
        pool.invoke(new SortTask(array, temp, 0, array.length - 1, threshold));
    }

    public void shutdown() {
        pool.shutdown();
    }

    private static class SortTask extends RecursiveAction {
        private final Student[] array;
        private final Student[] temp;
        private final int left;
        private final int right;
        private final int threshold;

        SortTask(Student[] array, Student[] temp, int left, int right, int threshold) {
            this.array = array;
            this.temp = temp;
            this.left = left;
            this.right = right;
            this.threshold = threshold;
        }

        @Override
        protected void compute() {
            if (left >= right) {
                return;
            }

            if (right - left + 1 <= threshold) {
                sequentialRangeSort(array, temp, left, right);
                return;
            }

            int mid = left + (right - left) / 2;

            SortTask leftTask = new SortTask(array, temp, left, mid, threshold);
            SortTask rightTask = new SortTask(array, temp, mid + 1, right, threshold);

            invokeAll(leftTask, rightTask);
            MergeSort.merge(array, temp, left, mid, right);
        }

        private static void sequentialRangeSort(Student[] array, Student[] temp,
                                                int left, int right) {
            if (left >= right) {
                return;
            }

            int mid = left + (right - left) / 2;
            sequentialRangeSort(array, temp, left, mid);
            sequentialRangeSort(array, temp, mid + 1, right);
            MergeSort.merge(array, temp, left, mid, right);
        }
    }
}
