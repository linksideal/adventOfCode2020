package adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aufgabe10 {

	private List<String> testInput;

	private Long testErwartungA;
	private Long ergebnisA;
	private Long testErwartungB;
	private Long ergebnisB;

	private List<String> input;

	public Aufgabe10(List<String> input, List<String> testInput) {
		this.input = input;
		this.testInput = testInput;
		testErwartungA = 10L * 22L;
		ergebnisA = 1876L;

		testErwartungB = 19208L;
		ergebnisB = 14173478093824L;
	}

	public void ermittle() {
		List<Long> testZahlen = testInput.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Long> zahlen = input.stream().map(Long::parseLong).collect(Collectors.toList());

		System.out.println("Test a) erfolgreich? " + product1Jolt3Jolts(testZahlen).equals(testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? " + product1Jolt3Jolts(zahlen).equals(ergebnisA));

		System.out.println("Test b) erfolgreich? " + numberOfDistinctArrangements(testZahlen).equals(testErwartungB));
		System.out.println("Aufgabe b) erfolgreich? " + numberOfDistinctArrangements(zahlen).equals(ergebnisB));
	}

	private Long product1Jolt3Jolts(List<Long> zahlen) {
		zahlen.add(0L);
		zahlen.add(Collections.max(zahlen) + 3L);
		Collections.sort(zahlen);
		List<Long> jumps = jumps(zahlen);
		Long numberOf1Jumps = jumps.stream().filter(zahl -> zahl == 1).count();
		Long numberOf3Jumps = jumps.stream().filter(zahl -> zahl == 3).count();
		return numberOf1Jumps * numberOf3Jumps;
	}

	private List<Long> jumps(List<Long> zahlen) {
		return zahlen.stream().map(zahl -> diffToNext(zahl, zahlen)).collect(Collectors.toList());
	}

	private Long diffToNext(Long zahl, List<Long> zahlen) {
		return getNext(zahl, zahlen) - zahl;
	}

	private int indexOfNext(Long zahl, List<Long> zahlen) {
		return Math.min(zahlen.indexOf(zahl) + 1, zahlen.size() - 1);
	}

	private Long getNext(Long zahl, List<Long> zahlen) {
		return zahlen.get(indexOfNext(zahl, zahlen));
	}

	private Long numberOfDistinctArrangements(List<Long> zahlen) {
		return consecutive1s(jumps(zahlen)).stream().map(this::combinations).reduce(1L, (a, b) -> a * b);
	}

	private List<Integer> consecutive1s(List<Long> zahlen) {
		Integer count = 0;
		List<Integer> counts = new ArrayList<>();
		for (Long zahl : zahlen) {
			if (zahl.equals(1L)) {
				count++;
			} else {
				if (!count.equals(0)) {
					counts.add(count);
					count = 0;
				}
			}
		}
		return counts;
	}

	private Long combinations(Integer consecutive1s) {
		switch (consecutive1s) {
		case 1:
			return 1L;
		case 2:
			return 2L;
		case 3:
			return 4L;
		case 4:
			return 7L;
		default:
			return 0L;
		}
	}

}
