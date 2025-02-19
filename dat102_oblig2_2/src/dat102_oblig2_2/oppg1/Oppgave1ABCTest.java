package dat102_oblig2_2.oppg1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import org.junit.jupiter.api.Assertions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Oppgave1ABCTest {

    // Antall repetisjoner for å beregne gjennomsnittstid
    private static final int REPS = 10;

    // Boolean-variabler slik at vi skriver header kun én gang per algoritme
    private static boolean printedAHeader = false;
    private static boolean printedBHeader = false;
    private static boolean printedCHeader = false;

    /**
     * Oppgave1A: insertionSortMinFront
     */
    @ParameterizedTest
    @Order(1)
    @ValueSource(ints = {1000, 5000, 10000, 20000})
    public void testInsertionSortMinFront(int size) {
        // Skriv ut header kun første gang
        if (!printedAHeader) {
            System.out.println("Resultat insertionSortMinFront (Oppgave1A):");
            System.out.println("| N        | Antall målinger | Målt tid (gjennomsnitt, ms) |");
            System.out.println("|----------|-----------------|-----------------------------|");
            printedAHeader = true;
        }

        // Mål faktisk tid (gjennomsnitt over REPS repetisjoner)
        double avgMs = measureTime(() -> {
            Integer[] array = generateRandomArray(size);
            Oppgave1A.insertionSortMinFront(array);
            // Verifiser at arrayet faktisk er sortert
            Assertions.assertTrue(isSorted(array));
        }, REPS);

        // Skriv ut en rad i tabellen med tre verdier
        printRow(size, REPS, avgMs);
    }

    /**
     * Oppgave1B: insertionSortTwoAtATime
     */
    @ParameterizedTest
    @Order(2)
    @ValueSource(ints = {1000, 5000, 10000, 20000})
    public void testInsertionSortTwoAtATime(int size) {
        // Skriv ut header kun første gang
        if (!printedBHeader) {
            System.out.println("\nResultat insertionSortTwoAtATime (Oppgave1B):");
            System.out.println("| N        | Antall målinger | Målt tid (gjennomsnitt, ms) |");
            System.out.println("|----------|-----------------|-----------------------------|");
            printedBHeader = true;
        }

        double avgMs = measureTime(() -> {
            Integer[] array = generateRandomArray(size);
            Oppgave1B.insertionSortTwoAtATime(array);
            Assertions.assertTrue(isSorted(array));
        }, REPS);

        printRow(size, REPS, avgMs);
    }

    /**
     * Oppgave1C: insertionSortMinFrontTwoAtATime
     */
    @ParameterizedTest
    @Order(3)
    @ValueSource(ints = {1000, 5000, 10000, 20000})
    public void testInsertionSortMinFrontTwoAtATime(int size) {
        // Skriv ut header kun første gang
        if (!printedCHeader) {
            System.out.println("\nResultat insertionSortMinFrontTwoAtATime (Oppgave1C):");
            System.out.println("| N        | Antall målinger | Målt tid (gjennomsnitt, ms) |");
            System.out.println("|----------|-----------------|-----------------------------|");
            printedCHeader = true;
        }

        double avgMs = measureTime(() -> {
            Integer[] array = generateRandomArray(size);
            Oppgave1C.insertionSortMinFrontTwoAtATime(array);
            Assertions.assertTrue(isSorted(array));
        }, REPS);

        printRow(size, REPS, avgMs);
    }

    /**
     * Genererer en array med tilfeldig innhold.
     */
    private Integer[] generateRandomArray(int size) {
        Random random = new Random(1234L); // fast seed for repeterbare tester
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }

    /**
     * Sjekker om tabellen er sortert i ikke-synkende rekkefølge.
     */
    private boolean isSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Måler tid for et sorteringskall (Runnable) over 'repetitions' repetisjoner.
     * Returverdi er gjennomsnittlig tid i millisekunder.
     */
    private double measureTime(Runnable sortCall, int repetitions) {
        long totalNano = 0;
        for (int i = 0; i < repetitions; i++) {
            long start = System.nanoTime();
            sortCall.run();
            long end = System.nanoTime();
            totalNano += (end - start);
        }
        double avgNano = (double) totalNano / repetitions;
        return avgNano / 1_000_000.0; // i millisekunder
    }

    // Utskriftsmetode for tabellrad
    private void printRow(int size, int repetitions, double averageMeasuredTime) {
        System.out.printf("| %-8d | %-15d | %-27.2f |\n",
                size, repetitions, averageMeasuredTime);
    }
}
