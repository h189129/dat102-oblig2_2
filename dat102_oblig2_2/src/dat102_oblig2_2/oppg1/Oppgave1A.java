package dat102_oblig2_2.oppg1;

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
            while (j >= 0 && a[j].compareTo(temp) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }
}
