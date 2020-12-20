package adventofcode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Exercise09 extends Exercise{
	
	public Exercise09() throws IOException {
		super("src/adventofcode/input9.txt", "src/adventofcode/testinput9.txt");
		
		exNumber = 9;
		
		expectationA = 104054607L; 
		expectationTestA = 127L;

		expectationTestB = 62L;
		expectationB = 13935797L;
	}

	@Override
	protected void solveA() {
		List<Long> testNumbers = parseNumbers(testInput);
		List<Long> numbers = parseNumbers(input);
		
		solutionTestA = findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(testNumbers, 5);
		solutionA = findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(numbers, 25);
	}

	@Override
	protected void solveB() {
		List<Long> testNumbers = parseNumbers(testInput);
		List<Long> numbers = parseNumbers(input);
		
		int[] testIndizes = findIndizesOfContiguousNumbersWhichSumToTarget(testNumbers, (Long) expectationTestA);
		List<Long> testSubList = testNumbers.subList(testIndizes[0], testIndizes[1]);
		solutionTestB = sumMaxAndMinOfList(testSubList);
		
		int[] indizes = findIndizesOfContiguousNumbersWhichSumToTarget(numbers, (Long) solutionA);
		List<Long> subList = numbers.subList(indizes[0], indizes[1]);
		solutionB = sumMaxAndMinOfList(subList);
		
	}
	
	private Long findFirstNumberWhichIsSumOfTwoNumbersFromPreamble(List<Long> zahlen, int sizeOfPreamble) {

		for (int i = sizeOfPreamble; i < zahlen.size(); i++) {
			List<Long> preamble = zahlen.subList(i - sizeOfPreamble, i);
			Long zahl = zahlen.get(i);
			if (Exercise01.ermittleZweiZahlen(zahl, preamble) == null) {
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
