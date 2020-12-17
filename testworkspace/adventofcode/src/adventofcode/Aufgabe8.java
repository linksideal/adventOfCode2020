package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe8 {

	List<String> testInputA;
	List<String> testInputB;

	int testErwartungA;
	int ergebnisA;

	int testErwartungB;
	int ergebnisB;

	List<String> input;

	public Aufgabe8(List<String> input) {
		this.input = input;
		testInputA = new ArrayList<>();
		testInputA.add("nop +0");
		testInputA.add("acc +1");
		testInputA.add("jmp +4");
		testInputA.add("acc +3");
		testInputA.add("jmp -3");
		testInputA.add("acc -99");
		testInputA.add("acc +1");
		testInputA.add("jmp -4");
		testInputA.add("acc +6");

		testErwartungA = 5;
		ergebnisA = 2051;

		testErwartungB = 8;
		ergebnisB = 2304;
	}

	public String ermittle() {
		Program program = new Program(input);
		Program testProgram = new Program(testInputA);

		testProgram.run();
		program.run();

		System.out.println("Test a) erfolgreich? " + (testProgram.getAccumulator() == testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? " + (program.getAccumulator() == ergebnisA));

		Program optimizedTestProgram = ProgramOptimizer.optimize(testProgram).get();
		Program optimizedProgram = ProgramOptimizer.optimize(program).get();

		System.out.println("Test b) erfolgreich? " + (optimizedTestProgram.getAccumulator() == testErwartungB));
		System.out.println("Aufgabe b) erfolgreich? " + (optimizedProgram.getAccumulator() == ergebnisB));

		return "a) " + program.getAccumulator() + ", b) " + optimizedProgram.getAccumulator();
	}

}
