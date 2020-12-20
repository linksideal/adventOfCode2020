package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Exercise {

	protected int exNumber;

	protected List<String> testInput;
	protected List<String> testInputB;
	protected List<String> input;

	protected Object expectationTestA;
	protected Object expectationA;
	protected Object solutionTestA;
	protected Object solutionA;
	protected Object expectationTestB;
	protected Object expectationB;
	protected Object solutionTestB;
	protected Object solutionB;

	public Exercise(String pathInput) throws IOException {
		this.input = readFile(pathInput);
	}
	
	public Exercise(String pathInput, String pathTestInput) throws IOException {
		this.input = readFile(pathInput);
		this.testInput = readFile(pathTestInput);
	}

	public Exercise(String pathInput, String pathTestInputA, String pathTestInputB) throws IOException {
		this.input = readFile(pathInput);
		this.testInput = readFile(pathTestInputA);
		this.testInputB = readFile(pathTestInputB);
	}

	public void printResults() throws Exception {
		System.out.println("==============================");
		System.out.println("Exercise " + exNumber);
		System.out.println("==============================");

		solveA();
		printResult("A)", solutionA, solutionTestA, expectationA, expectationTestA);

		solveB();
		printResult("B)", solutionB, solutionTestB, expectationB, expectationTestB);
	}

	private void printResult(String name, Object solution, Object solutionTest, Object expectation,
			Object expectationTest) {
		System.out.println(name);
		System.out.println("------------------------------");
		if (expectationTest != null) {
			System.out.println("Test    erfolgreich?      " + expectationTest.equals(solutionTest));
		} else {
			System.out.println("Test    erfolgreich?         -");
		}
		System.out.println("Aufgabe erfolgreich?      " + expectation.equals(solution));
	}

	protected abstract void solveA() throws Exception;

	protected abstract void solveB() throws Exception;

	private static List<String> readFile(String path) throws IOException {
		return Files.readAllLines(Paths.get(path));
	}

	protected List<Long> parseNumbers(List<String> input) {
		return input.stream().map(Long::parseLong).collect(Collectors.toList());
	}

}
