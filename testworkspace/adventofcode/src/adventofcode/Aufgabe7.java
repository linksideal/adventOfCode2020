package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe7 {

	List<String> testInputA;
	List<String> testInputB;

	int testErwartungA;
	int ergebnisA;

	int testErwartungB;
	int ergebnisB;

	List<String> input;

	public Aufgabe7(List<String> input) {
		this.input = input;
		testInputA = new ArrayList<>();
		testInputA.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
		testInputA.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
		testInputA.add("bright white bags contain 1 shiny gold bag.");
		testInputA.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
		testInputA.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
		testInputA.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
		testInputA.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
		testInputA.add("faded blue bags contain no other bags.");
		testInputA.add("dotted black bags contain no other bags.");
		testErwartungA = 4;
		ergebnisA = 235;
		
		testInputB = new ArrayList<>();
		testInputB.add("shiny gold bags contain 2 dark red bags.");
		testInputB.add("dark red bags contain 2 dark orange bags..");
		testInputB.add("dark orange bags contain 2 dark yellow bags.");
		testInputB.add("dark yellow bags contain 2 dark green bags.");
		testInputB.add("dark green bags contain 2 dark blue bags.");
		testInputB.add("dark blue bags contain 2 dark violet bags.");
		testInputB.add("dark violet bags contain no other bags.");
		testErwartungB = 126;
		ergebnisB = 158493;
	}

	public String ermittle() {
		Bags bags = new Bags(input);
		Bags testBagsA = new Bags(testInputA);
		Bags testBagsB = new Bags(testInputB);
		
		System.out
				.println("Test a) erfolgreich? " + (testBagsA.numberOfBagsThatCanContainShinyGoldBag() == testErwartungA));
		System.out.println("Aufgabe a) erfolgreich? " + (bags.numberOfBagsThatCanContainShinyGoldBag() == ergebnisA));
		System.out
		.println("Test b) erfolgreich? " + (testBagsB.numberOfBagsInsideBag("shiny gold") == testErwartungB));
		System.out.println("Aufgabe b) erfolgreich? " + (bags.numberOfBagsInsideBag("shiny gold") == ergebnisB));
		
		return "a) " + bags.numberOfBagsThatCanContainShinyGoldBag() + ", b) " + bags.numberOfBagsInsideBag("shiny gold");
	}

}
