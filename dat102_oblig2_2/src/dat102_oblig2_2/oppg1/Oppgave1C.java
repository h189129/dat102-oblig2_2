package dat102_oblig2_2.oppg1;

public class Oppgave1C {

    /**
     * 1) Finn og flytt minste element til indeks 0.
     * 2) Sett inn parvise elementer med vanlig innsetting.
     */
    public static void insertionSortMinFrontTwoAtATime(Integer[] a) {
        int n = a.length;
        if (n <= 1) return; // Ingen sortering om 0/1 element

        // 1. Flytt minste element til front
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[minIndex]) < 0) {
                minIndex = i;
            }
        }
        if (minIndex != 0) {
            bytt(a, 0, minIndex);
        }

        // 2. Behandle resten to og to
        for (int i = 2; i < n; i += 2) {
            // Hvis oddetall med ett element igjen:
            if (i == n - 1) {
                vanligInnsetting(a, i);
                break;
            }

            // Hent to elementer
            Integer e1 = a[i];
            Integer e2 = a[i + 1];

            // Finn minst/størst
            Integer minst = (e1.compareTo(e2) <= 0) ? e1 : e2;
            Integer storst = (minst == e1) ? e2 : e1;

            // Sett inn 'minst' (vanlig innsetting)
            a[i] = minst;
            vanligInnsetting(a, i);

            // Sett inn 'størst' (vanlig innsetting)
            a[i + 1] = storst;
            vanligInnsetting(a, i + 1);
        }
    }

    private static void vanligInnsetting(Integer[] a, int i) {
        Integer temp = a[i];
        int j = i - 1;
        while (j >= 0 && a[j].compareTo(temp) > 0) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = temp;
    }

    private static void bytt(Integer[] a, int i, int j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
