package dat102_oblig2_2.oppg2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class Oppgave2ATest {

    private Integer[] generateRandomArray(int size) {
        Random random = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000); // Tall mellom 0 og 99999
        }
        return array;
    }

    private double measureTime(Runnable sortFunction, Integer[] array) {
        long startTime = System.nanoTime();
        sortFunction.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; // Konverterer til millisekunder
    }

    private double calculateTheoreticalTime(double c, int n, String complexity) {
        if ("n^2".equals(complexity)) {
            return c * Math.pow(n, 2);
        } else if ("n*log(n)".equals(complexity)) {
            return c * n * Math.log(n) / Math.log(2); // log₂(n)
        }
        return 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {32000, 64000, 128000})
    public void testInsertionSort(int size) {
        runAndPrintResults("Insertion Sort", size, "n^2", Oppgave2A::insertionSort);
    }

    @ParameterizedTest
    @ValueSource(ints = {32000, 64000, 128000})
    public void testSelectionSort(int size) {
        runAndPrintResults("Selection Sort", size, "n^2", Oppgave2A::selectionSort);
    }

    @ParameterizedTest
    @ValueSource(ints = {32000, 64000, 128000})
    public void testQuickSort(int size) {
        runAndPrintResults("Quick Sort", size, "n*log(n)", Oppgave2A::quickSort);
    }

    @ParameterizedTest
    @ValueSource(ints = {32000, 64000, 128000})
    public void testMergeSort(int size) {
        runAndPrintResults("Merge Sort", size, "n*log(n)", Oppgave2A::mergeSort);
    }

    private void runAndPrintResults(String algorithmName, int size, String complexity, SortFunction sortFunction) {
        int repetitions = 10; // Antall repetisjoner for å få en gjennomsnittlig tid
        double measuredTimeSum = 0;

        // Første kjøring for å bestemme c-verdien
        Integer[] testArray = generateRandomArray(size);
        double c = determineConstant(testArray, sortFunction, complexity);

        // Kjører sorteringsalgoritmen flere ganger og summérer tiden
        for (int i = 0; i < repetitions; i++) {
            Integer[] arrayCopy = generateRandomArray(size);
            measuredTimeSum += measureTime(() -> sortFunction.sort(arrayCopy), arrayCopy);

            // Verifiserer at tabellen er sortert
            Assertions.assertTrue(isSorted(arrayCopy));
        }

        // Beregner gjennomsnittlig tid
        double averageMeasuredTime = measuredTimeSum / repetitions;

        // Beregner teoretisk tid
        double theoreticalTime = calculateTheoreticalTime(c, size, complexity);

        // Skriver ut resultatet i tabellformat
        if (size == 32000) {
            printHeader(algorithmName);
        }
        printRow(size, repetitions, averageMeasuredTime, theoreticalTime);

        if (size == 128000) {
            System.out.println(); // Tom linje mellom algoritmer
        }
    }

    private double determineConstant(Integer[] array, SortFunction sortFunction, String complexity) {
        double measuredTime = measureTime(() -> sortFunction.sort(array), array);
        int n = array.length;
        if ("n^2".equals(complexity)) {
            return measuredTime / Math.pow(n, 2);
        } else if ("n*log(n)".equals(complexity)) {
            return measuredTime / (n * Math.log(n) / Math.log(2)); // log₂(n)
        }
        return 0;
    }

    private boolean isSorted(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void printHeader(String algorithmName) {
        System.out.println("Resultat " + algorithmName + ":");
        System.out.println("| N        | Antall målinger | Målt tid (gjennomsnitt, ms) | Teoretisk tid (c*f(n), ms) |");
        System.out.println("|----------|-----------------|-----------------------------|----------------------------|");
    }

    private void printRow(int size, int repetitions, double averageMeasuredTime, double theoreticalTime) {
        System.out.printf("| %-8d | %-15d | %-27.2f | %-26.2f |\n", size, repetitions, averageMeasuredTime, theoreticalTime);
    }

    @FunctionalInterface
    interface SortFunction {
        void sort(Integer[] array);
    }
}