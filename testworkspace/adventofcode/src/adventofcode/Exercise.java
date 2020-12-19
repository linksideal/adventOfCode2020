package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Exercise {

	protected List<String> testInput;
	protected List<String> input;

	protected Object expectationTestA;
	protected Object expectationA;
	protected Object solutionTestA;
	protected Object solutionA;
	protected Object expectationTestB;
	protected Object expectationB;
	protected Object solutionTestB;
	protected Object solutionB;
	
	public Exercise(String pathInput, String pathTestInput) throws IOException {
		this.input = readFile(pathInput);
		this.testInput = readFile(pathTestInput);
	}

	public void printResults() {
		solveA();
		System.out.println("Test a) erfolgreich? " + expectationTestA.equals(solutionTestA));
		System.out.println("Aufgabe a) erfolgreich? " + expectationA.equals(solutionA));

		solveB();
		System.out.println("Test b) erfolgreich? " + expectationTestB.equals(solutionTestB));
		System.out.println("Aufgabe b) erfolgreich? " + expectationB.equals(solutionB));
	}
	
	protected void solveA() {
		throw new UnsupportedOperationException();
	}
	
	protected void solveB() {
		throw new UnsupportedOperationException();
	}
	
	private static List<String> readFile(String path) throws IOException {
		return Files.readAllLines(Paths.get(path));
	}

}
