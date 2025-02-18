package dat102_oblig2_2.oppg1;

import java.util.Random;

public class Oppgave1C {

    /**
     * Kombinasjon av:
     *  - Flytte minste element til indeks 0 (triks fra oppgave 1a)
     *  - Sette inn to elementer av gangen (oppgave 1b)
     * 
     * Håndterer også oddetalls-lengde ved å sette inn 
     * det siste (enkelt) elementet på vanlig måte.
     */
    public static void insertionSortMinFrontTwoAtATime(Integer[] a) {
        int n = a.length;
        if (n <= 1) return; // Ingenting å sortere om 0 eller 1 element
        
        // 1. Finner minste element og flytter det foran
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[minIndex]) < 0) {
                minIndex = i;
            }
        }
        if (minIndex != 0) {
            bytt(a, 0, minIndex);
        }

        // 2. Setter inn to og to elementer fra i = 2 og oppover
        for (int i = 2; i < n; i += 2) {
            // Hvis det bare er ett element igjen (oddetall)
            if (i == n - 1) {
                vanligInnsetting(a, i);
                break;
            }
            // Vi har to elementer a[i] og a[i + 1]
            Integer e1 = a[i];
            Integer e2 = a[i + 1];

            // Finner minst og størst
            Integer minst, storst;
            if (e1.compareTo(e2) <= 0) {
                minst = e1;
                storst = e2;
            } else {
                minst = e2;
                storst = e1;
            }

            // 2a. Setter inn 'størst' først ved å flytte elementer to plasser
            int j = i - 1;
            while (a[j].compareTo(storst) > 0) {
                a[j + 2] = a[j];
                j--;
            }
            a[j + 1] = storst;

            // 2b. Setter inn 'minst' med vanlig (1-plass) innsetting
            while (a[j].compareTo(minst) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = minst;
        }
    }

    /**
     * Hjelpemetode for vanlig innsetting av a[i] i den sorterte delen a[0..i-1].
     * Dropper j >= 0-sjekken fordi a[0] er garantert å være minst.
     */
    private static void vanligInnsetting(Integer[] a, int i) {
        Integer temp = a[i];
        int j = i - 1;
        while (a[j].compareTo(temp) > 0) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = temp;
    }

    /**
     * Hjelpemetode for å bytte om på to elementer i tabellen.
     */
    private static void bytt(Integer[] a, int i, int j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // 1. Parametere
        Random tilfeldig = new Random(1234L);
        int n = 40000;   // Antall elementer i hver rad
        int antal = 10;  // Antall rader

        // 2. Opprett og fyll 2D-array [antal][n] med tilfeldige tall
        Integer[][] a = new Integer[antal][n];
        for (int i = 0; i < antal; i++){
            for (int j = 0; j < n; j++){
                a[i][j] = tilfeldig.nextInt();
            }
        }

        // 3. Starter tidsmåling
        long start = System.nanoTime();

        // 4. Sortérer hver rad
        for (int i = 0; i < antal; i++){
            insertionSortMinFrontTwoAtATime(a[i]);
        }

        // 5. Slutter tidsmåling
        long slutt = System.nanoTime();

        // 6. Regner ut varighet i sekunder
        long varighetNs = slutt - start;
        double varighetSek = varighetNs / 1_000_000_000.0;

        // 7. Skriver ut varighet med 6 desimaler
        System.out.printf("Sortering av %d x %d elementer tok %.6f sekunder.%n",
                          antal, n, varighetSek);
    }
}
