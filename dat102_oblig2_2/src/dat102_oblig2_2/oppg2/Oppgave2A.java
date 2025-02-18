package dat102_oblig2_2.oppg2;

import java.util.Arrays;

public class Oppgave2A {

    // Insertion Sort
    public static void insertionSort(Integer[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Integer key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (array[j] < array[minIdx])
                    minIdx = j;
            Integer temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    // Quick Sort
    public static void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(Integer[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(Integer[] array, int low, int high) {
        Integer pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Merge Sort
    public static void mergeSort(Integer[] array) {
        if (array.length <= 1) return;
        int mid = array.length / 2;
        Integer[] left = Arrays.copyOfRange(array, 0, mid);
        Integer[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(Integer[] result, Integer[] left, Integer[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) result[k++] = left[i++];
            else result[k++] = right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }
}