package adventofcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Exercise06 extends Exercise {

	public Exercise06() throws IOException {
		super("src/adventofcode/input6.txt", "src/adventofcode/testinput6.txt");

		exNumber = 6;

		expectationTestA = 11;
		expectationA = 6551;

		expectationTestB = 6;
		expectationB = 3358;
	}

	@Override
	protected void solveA() {
		solutionTestA = numberOfQuestionsSomeoneAnswered(testInput);
		solutionA = numberOfQuestionsSomeoneAnswered(input);
	}

	@Override
	protected void solveB() {
		solutionTestB = numberOfQuestionsEveryoneAnswered(testInput);
		solutionB = numberOfQuestionsEveryoneAnswered(input);
	}

	private int numberOfQuestionsSomeoneAnswered(List<String> input) {
		return Arrays.asList(String.join("X", input).split("XX")).stream().map(x -> x.replace("X", ""))
				.map(x -> x.chars().mapToObj(e -> (char) e).collect(Collectors.toList()))
				.map(x -> x.stream().collect(Collectors.toSet())).map(x -> x.size()).mapToInt(Integer::intValue).sum();
	}

	private int numberOfQuestionsEveryoneAnswered(List<String> input) {

		return Arrays.asList(String.join("X", input).split("XX")).stream().map(x -> x.split("X")).map(Arrays::asList)
				.map(x -> x.stream().map(y -> y.chars().mapToObj(e -> (char) e)).map(z -> z.collect(Collectors.toSet()))
						.collect(Collectors.toList()))
				.map(this::intersec).map(Set::size).mapToInt(Integer::intValue).sum();

	}

	private Set<Character> intersec(List<Set<Character>> list) {
		if (list.size() == 1) {
			return list.get(0);
		}
		list.get(list.size() - 1).retainAll(intersec(list.subList(0, list.size() - 1)));
		return list.get(list.size() - 1);
	}

}
