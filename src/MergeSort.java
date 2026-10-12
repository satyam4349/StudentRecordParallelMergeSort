public class MergeSort {

    private MergeSort() {}

    public static void sort(Student[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Student[] temp = new Student[array.length];
        sort(array, temp, 0, array.length - 1);
    }

    private static void sort(Student[] array, Student[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sort(array, temp, left, mid);
        sort(array, temp, mid + 1, right);
        merge(array, temp, left, mid, right);
    }

    static void merge(Student[] array, Student[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (array[i].getMarks() >= array[j].getMarks()) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int p = left; p <= right; p++) {
            array[p] = temp[p];
        }
    }
}
