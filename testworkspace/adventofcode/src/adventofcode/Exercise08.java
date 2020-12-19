package adventofcode;

import java.io.IOException;

public class Exercise08 extends Exercise{

	public Exercise08() throws IOException {
		super("src/adventofcode/input8.txt", "src/adventofcode/testinput8.txt");
		
		exNumber = 8;
		
		expectationA = 2051; 
		expectationTestA = 5;

		expectationTestB = 8;
		expectationB = 2304;
	}

	@Override
	protected void solveA() {
		Program program = new Program(input);
		Program testProgram = new Program(testInput);
		
		testProgram.run();
		program.run();
		
		solutionTestA = testProgram.getAccumulator();
		solutionA = program.getAccumulator();
	}

	@Override
	protected void solveB() {
		Program program = new Program(input);
		Program testProgram = new Program(testInput);
		
		Program optimizedTestProgram = ProgramOptimizer.optimize(testProgram).get();
		Program optimizedProgram = ProgramOptimizer.optimize(program).get();

		solutionTestB = optimizedTestProgram.getAccumulator();
		solutionB = optimizedProgram.getAccumulator();
	}

}
