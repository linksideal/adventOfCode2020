package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe7 {

	List<String> testInput;

	int testErwartungA;
	int ergebnisA;

	int testErwartungB;

	List<String> input;

	public Aufgabe7(List<String> input) {
		this.input = input;
		testInput = new ArrayList<>();
		testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
		testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
		testInput.add("bright white bags contain 1 shiny gold bag.");
		testInput.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
		testInput.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
		testInput.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
		testInput.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
		testInput.add("faded blue bags contain no other bags.");
		testInput.add("dotted black bags contain no other bags.");
		testErwartungA = 4;
		ergebnisA = 235;
	}

	public int a() {
		Bags bags = new Bags(input);
		Bags testBags= new Bags(testInput);
		
		System.out
				.println("Test erfolgreich? " + (testBags.numberOfBagsThatCanContainShinyGoldBag() == testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? " + (bags.numberOfBagsThatCanContainShinyGoldBag() == ergebnisA));
		
		return bags.numberOfBagsThatCanContainShinyGoldBag();
	}

}
