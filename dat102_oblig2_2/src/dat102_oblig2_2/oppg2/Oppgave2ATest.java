package dat102_oblig2_2.oppg2;

import org.junit.Test;

import static org.junit.Assert.*;

public class Oppgave2ATest {

    @Test
    public void testInsertionSort() {
        Integer[] arr = {3, 1, 4, 1, 5};
        Oppgave2A.insertionSort(arr);
        assertArrayEquals(new Integer[]{1, 1, 3, 4, 5}, arr);
    }

    @Test
    public void testSelectionSort() {
        Integer[] arr = {5, 3, 6, 2, 10};
        Oppgave2A.selectionSort(arr);
        assertArrayEquals(new Integer[]{2, 3, 5, 6, 10}, arr);
    }

    @Test
    public void testQuickSort() {
        Integer[] arr = {9, 3, 5, 1, 4};
        Oppgave2A.quickSort(arr);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 9}, arr);
    }

    @Test
    public void testMergeSort() {
        Integer[] arr = {8, 4, 2, 7, 1};
        Oppgave2A.mergeSort(arr);
        assertArrayEquals(new Integer[]{1, 2, 4, 7, 8}, arr);
    }
}
