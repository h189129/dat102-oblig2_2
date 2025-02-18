package dat102_oblig2_2.oppg1;
import java.util.Random;

public class Oppgave1A {

    /**
     * Insertion sort som flytter minste element fremst før sorteringen starter.
     */
    public static void insertionSortMinFront(Integer[] a) {
        int n = a.length;
        if (n <= 1) return;  // Ingen sortering nødvendig for 0/1 element

        // 1. Finner posisjonen til det minste elementet
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[minIndex]) < 0) {
                minIndex = i;
            }
        }

        // 2. Bytter det minste elementet fremst i tabellen (posisjon 0)
        if (minIndex != 0) {
            Integer temp = a[0];
            a[0] = a[minIndex];
            a[minIndex] = temp;
        }

        // 3. Kjører innsettingssortering fra indeks 2 og utover.
        for (int i = 2; i < n; i++) {
            Integer temp = a[i];
            int j = i - 1;
            while (a[j].compareTo(temp) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    public static void main(String[] args) {

        // Parametre for eksperimentet
        Random tilfeldig = new Random(1234L);
        int n = 40000;     // Antall elementer pr "rad"
        int antall = 10;    // Antall rader vi vil sortere

        // Oppretter et 2D-array [antal][n]
        Integer[][] a = new Integer[antall][n];

        // Fyller hver rad med tilfeldige heltall
        for (int i = 0; i < antall; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = tilfeldig.nextInt();  
            }
        }

        // Starter tidsmåling med System.nanoTime()
        long start = System.nanoTime();

        // Sortérer hver rad
        for (int i = 0; i < antall; i++) {
            insertionSortMinFront(a[i]);
        }

        // Avslutter tidsmåling
        long slutt = System.nanoTime();

        // Regner ut forbrukte nanosekunder -> sekunder
        long tidsforbrukNs = slutt - start;
        double tidsforbrukSekunder = tidsforbrukNs / 1_000_000_000.0;

        // Skriver ut resultatet i sekunder med 6 desimaler
        System.out.printf("Sortering av %d elementer tok %.6f sekunder%n",
                          (antall * n), tidsforbrukSekunder);
    }
}

