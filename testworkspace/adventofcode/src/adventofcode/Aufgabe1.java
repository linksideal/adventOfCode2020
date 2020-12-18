package adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Aufgabe1 {

	private List<String> testInput;

	private long testErwartungA;
	private long ergebnisA;

	private long testErwartungB;
	private long ergebnisB;

	private List<String> input;

	public Aufgabe1(List<String> input) {
		this.input = input;
		testInput = new ArrayList<>();
		testInput.add("1721");
		testInput.add("979");
		testInput.add("366");
		testInput.add("299");
		testInput.add("675");
		testInput.add("1456");

		testErwartungA = 514579;
		ergebnisA = 703131;

		testErwartungB = 241861950;
		ergebnisB = 272423970;
	}

	public String ermittle() {
		List<Long> zahlen = input.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Long> testZahlen = testInput.stream().map(Long::parseLong).collect(Collectors.toList());

		System.out.println("Test a) erfolgreich? " + (ermittleProduktZweiZahlen(2020, testZahlen) == testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? " + (ermittleProduktZweiZahlen(2020, zahlen) == ergebnisA));

		System.out.println("Test b) erfolgreich? " + (ermittleProduktDreiZahlen(testZahlen) == testErwartungB));
		System.out.println("Aufgabe b) erfolgreich? " + (ermittleProduktDreiZahlen(zahlen) == ergebnisB));

		return "a) " + ermittleProduktZweiZahlen(2020, zahlen) + ", b) " + ermittleProduktDreiZahlen(zahlen);
	}

	public static long ermittleProduktZweiZahlen(long zielwert, List<Long> liste) {
		long[] zweiZahlen = ermittleZweiZahlen(zielwert, liste);
		if( zweiZahlen == null) {
			return -1;
		} else {
			return zweiZahlen[0] * zweiZahlen[1];
		}
	}

	public static long[] ermittleZweiZahlen(long zielwert, List<Long> liste) {
		List<Long> listeKlon = new ArrayList<>(liste);
		Collections.sort(listeKlon);
		int untererIndex = 0;
		int obererIndex = listeKlon.size() - 1;
		while (untererIndex < listeKlon.size() && obererIndex > untererIndex) {
			while (obererIndex >= 0 && obererIndex > untererIndex) {
				long sum = listeKlon.get(obererIndex) + listeKlon.get(untererIndex);
				if (sum < zielwert) {
					untererIndex++;
				} else if (sum > zielwert) {
					obererIndex--;
				} else {
					return new long[] { listeKlon.get(obererIndex), listeKlon.get(untererIndex) };
				}
			}
		}
		return null;
	}

	private static long ermittleProduktDreiZahlen(List<Long> zahlen) {
		Collections.sort(zahlen);
		int untererIndex = 0;
		int obererIndex = zahlen.size() - 1;
		int mittlererIndex = 1;
		while (obererIndex > 0) {
			long sum = zahlen.get(untererIndex) + zahlen.get(mittlererIndex) + zahlen.get(obererIndex);
			if (sum > 2020) {
				obererIndex--;
			} else if (sum == 2020) {
				return zahlen.get(untererIndex) * zahlen.get(mittlererIndex) * zahlen.get(obererIndex);
			} else {
				long i = ermittleProduktZweiZahlen(2020 - zahlen.get(obererIndex), zahlen.subList(0, zahlen.size() - 1));
				if (i > -1) {
					return i * zahlen.get(obererIndex);
				} else {
					obererIndex--;
				}
			}
		}
		return 1;
	}

}
