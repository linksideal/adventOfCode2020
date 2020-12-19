package adventofcode;

import java.io.IOException;

public class Exercise07 extends Exercise{

	public Exercise07() throws IOException {
		super("src/adventofcode/input7.txt", "src/adventofcode/testinput7a.txt", "src/adventofcode/testinput7b.txt");
		
		exNumber = 7;
		
		expectationTestA = 4;
		expectationA = 235;
		
		expectationTestB = 126;
		expectationB = 158493;
	}

	@Override
	protected void solveA() {
		Bags bags = new Bags(input);
		Bags testBagsA = new Bags(testInput);
		solutionTestA = testBagsA.numberOfBagsThatCanContainShinyGoldBag();
		solutionA = bags.numberOfBagsThatCanContainShinyGoldBag();
	}

	@Override
	protected void solveB() {
		Bags bags = new Bags(input);
		Bags testBagsB = new Bags(testInputB);
		solutionTestB = testBagsB.numberOfBagsInsideBag("shiny gold");
		solutionB = bags.numberOfBagsInsideBag("shiny gold");
	}

}
