package adventofcode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Aufgabe9 {

	private List<String> testInput;

	private Long testErwartungA;
	private Long ergebnisA;

	private Long testErwartungB;
	private Long ergebnisB;

	private List<String> input;

	public Aufgabe9(List<String> input, List<String> testInput) {
		this.input = input;
		this.testInput = testInput;
		testErwartungA = 127L;
		ergebnisA = 104054607L;

		testErwartungB = 62L;
		ergebnisB = 13935797L;
	}

	public String ermittle() {
		List<Long> testZahlen = testInput.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Long> zahlen = input.stream().map(Long::parseLong).collect(Collectors.toList());

		System.out.println("Test a) erfolgreich? "
				+ findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(testZahlen, 5).equals(testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? "
				+ findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(zahlen, 25).equals(ergebnisA));

		int[] testIndizes = findIndizesOfContiguousNumbersWhichSumToTarget(testZahlen, testErwartungA);
		List<Long> testSubList = testZahlen.subList(testIndizes[0], testIndizes[1]);
		System.out.println("Test b) erfolgreich? " + sumMaxAndMinOfList(testSubList).equals(testErwartungB));
		
		int[] indizes = findIndizesOfContiguousNumbersWhichSumToTarget(zahlen, ergebnisA);
		List<Long> subList = zahlen.subList(indizes[0], indizes[1]);
		System.out.println("Aufgabe b) erfolgreich? " + sumMaxAndMinOfList(subList).equals(ergebnisB));

		return "a) " + findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(zahlen, 25) + ", b) " + sumMaxAndMinOfList(subList);
	}

	private Long findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(List<Long> zahlen, int sizeOfPreamble) {

		for (int i = sizeOfPreamble; i < zahlen.size(); i++) {
			List<Long> preamble = zahlen.subList(i - sizeOfPreamble, i);
			Long zahl = zahlen.get(i);
			if (Aufgabe1.ermittleZweiZahlen(zahl, preamble) == null) {
				return zahl;
			}
		}
		return null;
	}

	private int[] findIndizesOfContiguousNumbersWhichSumToTarget(List<Long> zahlen, Long target) {
		int untererIndex = 0;
		int obererIndex = 0;
		while (untererIndex < zahlen.size()) {
			while (obererIndex < zahlen.size()) {
				Long sum = sum(zahlen, untererIndex, obererIndex);
				if (sum.equals(target)) {
					return new int[] { untererIndex, obererIndex - 1 };
				}
				if (sum < target) {
					obererIndex++;
				}
				if (sum > target) {
					untererIndex++;
				}
			}
		}
		return null;
	}

	private Long sum(List<Long> zahlen, int indexVonInkl, int indexBisExkl) {
		Long sum = 0L;
		for (int i = indexVonInkl; i < indexBisExkl; i++) {
			sum += zahlen.get(i);
		}
		return sum;
	}
	
	private Long sumMaxAndMinOfList(List<Long> zahlen) {
		Long max = Collections.max(zahlen);
		Long min = Collections.min(zahlen);
		return min+max;
	}

}
