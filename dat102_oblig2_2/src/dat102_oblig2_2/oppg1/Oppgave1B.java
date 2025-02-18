package dat102_oblig2_2.oppg1;

import java.util.Random;

public class Oppgave1B {

    /**
     * Metode: "to-element-innsettingssortering" (fra oppgave 1b).
     * a[0..1] sorteres først, deretter behandles resten av tabellen parvis.
     */
    public static void insertionSortTwoAtATime(Integer[] a) {
        int n = a.length;
        if (n <= 1) return;

        // Sortérer de to første elementene (om vi har minst to)
        if (n >= 2 && a[1] < a[0]) {
            bytt(a, 0, 1);
        }

        // Gå gjennom resten av arrayet to elementer av gangen
        for (int i = 2; i < n; i += 2) {
            // Hvis vi får ett element igjen (oddetall): bruk vanlig innsetting
            if (i == n - 1) {
                vanligInnsetting(a, i);
                break;
            }

            // 1. Hent de to elementene
            Integer e1 = a[i];
            Integer e2 = a[i + 1];

            // 2. Finn hvem som er minst og størst
            Integer minst, storst;
            if (e1 <= e2) {
                minst = e1;
                storst = e2;
            } else {
                minst = e2;
                storst = e1;
            }

            // 3. Sett inn 'størst' ved å flytte elementer to plasser til høyre
            int j = i - 1;
            while (j >= 0 && a[j] > storst) {
                a[j + 2] = a[j];
                j--;
            }
            a[j + 1] = storst;

            // 4. Sett inn 'minst' ved å flytte elementer én plass
            while (j >= 0 && a[j] > minst) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = minst;
        }
    }

    /**
     * Hjelpemetode: Setter inn a[i] på riktig plass i den sorterte delen a[0..i-1].
     */
    private static void vanligInnsetting(Integer[] a, int i) {
        Integer temp = a[i];
        int j = i - 1;
        while (j >= 0 && a[j] > temp) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = temp;
    }

    /**
     * Hjelpemetode: Bytter om a[i] og a[j].
     */
    private static void bytt(Integer[] a, int i, int j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // 1. Generer data
        Random tilfeldig = new Random(1234L); 
        int n = 40000;    // Antall elementer per rad
        int antal = 10;   // Antall rader

        // Opprett et 2D-array [antal][n]
        Integer[][] a = new Integer[antal][n];

        // Fyll hver rad med tilfeldige tall
        for (int i = 0; i < antal; i++){
            for (int j = 0; j < n; j++){
                a[i][j] = tilfeldig.nextInt();
            }
        }

        // 2. Start tidsmåling
        long start = System.nanoTime();

        // 3. Sortér hver rad
        for (int i = 0; i < antal; i++){
            insertionSortTwoAtATime(a[i]);
        }

        // 4. Slutt tidsmåling
        long slutt = System.nanoTime();

        // 5. Regn ut varighet i sekunder, med 6 desimaler
        long varighetNs = slutt - start;
        double varighetSek = varighetNs / 1_000_000_000.0;

        System.out.printf("Sortering av %d x %d elementer tok %.6f sekunder.%n",
                          antal, n, varighetSek);
    }
}
