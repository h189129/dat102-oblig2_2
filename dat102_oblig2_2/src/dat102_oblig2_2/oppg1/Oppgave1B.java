package dat102_oblig2_2.oppg1;

public class Oppgave1B {

    /**
     * "To-element-innsettingssortering": 
     * Sorterer først a[0..1], deretter tar den to og to elementer.
     * Denne versjonen unngår overskriving ved å bruke to *separate* j-variabler.
     */
	public static void insertionSortTwoAtATime(Integer[] a) {
	    int n = a.length;
	    if (n <= 1) return;

	    // Sortérer de to første (om det finnes to)
	    if (n >= 2 && a[1] < a[0]) {
	        bytt(a, 0, 1);
	    }

	    // Går gjennom resten to og to
	    for (int i = 2; i < n; i += 2) {
	        // Om oddetall, tar siste med vanlig innsetting
	        if (i == n - 1) {
	            vanligInnsetting(a, i);
	            break;
	        }
	        Integer e1 = a[i];
	        Integer e2 = a[i + 1];

	        Integer minst = (e1 <= e2) ? e1 : e2;
	        Integer storst = (minst == e1) ? e2 : e1;

	        // Setter inn minst med vanlig innsetting
	        a[i] = minst;
	        vanligInnsetting(a, i);

	        // Setter inn storst med vanlig innsetting
	        a[i + 1] = storst;
	        vanligInnsetting(a, i + 1);
	    }
	}


    /**
     * Hjelpemetode: Vanlig innsetting av a[i] i den sorterte delen a[0..i-1].
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
}
