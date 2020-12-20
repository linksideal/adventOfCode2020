package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise01 extends Exercise {

	public Exercise01() throws IOException {
		super("src/adventofcode/input1.txt", "src/adventofcode/testinput1.txt");
		
		exNumber = 1;

		expectationTestA = 514579L;
		expectationA = 703131L;

		expectationTestB = 241861950L;
		expectationB = 272423970L;
	}

	@Override
	protected void solveA() throws Exception {
		List<Long> zahlen = input.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Long> testZahlen = testInput.stream().map(Long::parseLong).collect(Collectors.toList());

		solutionTestA = ermittleProduktZweiZahlen(2020, testZahlen);
		solutionA = ermittleProduktZweiZahlen(2020, zahlen);
	}

	@Override
	protected void solveB() throws Exception {
		List<Long> zahlen = input.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Long> testZahlen = testInput.stream().map(Long::parseLong).collect(Collectors.toList());
		
		solutionTestB = ermittleProduktDreiZahlen(testZahlen);
		solutionB = ermittleProduktDreiZahlen(zahlen);

	}

	public static long ermittleProduktZweiZahlen(long zielwert, List<Long> liste) {
		long[] zweiZahlen = ermittleZweiZahlen(zielwert, liste);
		if (zweiZahlen == null) {
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
				long i = ermittleProduktZweiZahlen(2020 - zahlen.get(obererIndex),
						zahlen.subList(0, zahlen.size() - 1));
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
