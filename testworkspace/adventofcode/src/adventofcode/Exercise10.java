package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise10 extends Exercise{

	public Exercise10() throws IOException {

		super("src/adventofcode/input10.txt", "src/adventofcode/testinput10.txt");
		
		exNumber = 10;
		
		expectationA = 1876L; 
		expectationTestA = 10L * 22L;

		expectationTestB = 19208L;
		expectationB = 14173478093824L;
	}
	
	@Override
	protected void solveA() {
		List<Long> testNumbers = parseNumbers(testInput);
		List<Long> numbers = parseNumbers(input);

		solutionTestA = product1Jolt3Jolts(testNumbers);
		solutionA = product1Jolt3Jolts(numbers);
	}
	
	@Override
	protected void solveB() {
		List<Long> testNumbers = parseNumbers(testInput);
		List<Long> numbers = parseNumbers(input);

		solutionTestB = numberOfDistinctArrangements(testNumbers);
		solutionB = numberOfDistinctArrangements(numbers);
	}	
	
	private void sortAndAddStartAndEnd(List<Long> numbers) {
		numbers.add(0L);
		numbers.add(Collections.max(numbers) + 3L);
		Collections.sort(numbers);
	}
	
	private Long product1Jolt3Jolts(List<Long> numbers) {
		sortAndAddStartAndEnd(numbers);
		List<Long> jumps = jumps(numbers);
		Long numberOf1Jumps = jumps.stream().filter(number -> number == 1).count();
		Long numberOf3Jumps = jumps.stream().filter(number -> number == 3).count();
		return numberOf1Jumps * numberOf3Jumps;
	}

	private List<Long> jumps(List<Long> numbers) {
		return numbers.stream().map(number -> diffToNext(number, numbers)).collect(Collectors.toList());
	}

	private Long diffToNext(Long number, List<Long> numbers) {
		return getNext(number, numbers) - number;
	}

	private int indexOfNext(Long number, List<Long> numbers) {
		return Math.min(numbers.indexOf(number) + 1, numbers.size() - 1);
	}

	private Long getNext(Long number, List<Long> numbers) {
		return numbers.get(indexOfNext(number, numbers));
	}

	private Long numberOfDistinctArrangements(List<Long> numbers) {
		sortAndAddStartAndEnd(numbers);
		return consecutive1s(jumps(numbers)).stream().map(this::combinations).reduce(1L, (a, b) -> a * b);
	}

	private List<Integer> consecutive1s(List<Long> numbers) {
		Integer count = 0;
		List<Integer> counts = new ArrayList<>();
		for (Long number : numbers) {
			if (number.equals(1L)) {
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
