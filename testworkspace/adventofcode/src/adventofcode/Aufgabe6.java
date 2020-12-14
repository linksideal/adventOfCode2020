package adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Aufgabe6 {
	
	List<String> testInput;
	
	int testErwartungA;
	
	int testErwartungB;
	
	List<String> input;

	public Aufgabe6(List<String> input) {
		this.input = input;
		testInput = new ArrayList<>();
		testInput.add("abc");
		testInput.add("");
		testInput.add("a");
		testInput.add("b");
		testInput.add("c");
		testInput.add("");
		testInput.add("ab");
		testInput.add("ac");
		testInput.add("");
		testInput.add("a");
		testInput.add("a");
		testInput.add("a");
		testInput.add("a");
		testInput.add("");
		testInput.add("b");
		testErwartungA = 11;
		
		testErwartungB = 6;
	}

	public int a() {
		System.out.println("Test erfolgreich? " + (numberOfQuestionsSomeoneAnswered(testInput) == testErwartungA));
		return numberOfQuestionsSomeoneAnswered(input);
	}
	
	public int b() {
		System.out.println("Test erfolgreich? " + (numberOfQuestionsEveryoneAnswered(testInput) == testErwartungB));
		return numberOfQuestionsEveryoneAnswered(input);
	}

	private int numberOfQuestionsSomeoneAnswered(List<String> input) {
		return Arrays.asList(String.join("X", input).split("XX")).stream()
				.map(x -> x.replace("X", ""))
				.map(x -> x.chars().mapToObj(e -> (char) e).collect(Collectors.toList()))
				.map(x -> x.stream().collect(Collectors.toSet()))
				.map(x -> x.size())
				.mapToInt(Integer::intValue)
				.sum();
	}
	
	private int numberOfQuestionsEveryoneAnswered(List<String> input) {
		
		return Arrays.asList(
				String.join("X", input)
				.split("XX"))
				.stream()
				.map(x -> x.split("X"))
				.map(Arrays::asList)
				.map(
						x -> x.stream()
						.map(y-> y.chars().mapToObj(e -> (char) e))
						.map(z->z.collect(Collectors.toSet()))
						.collect(Collectors.toList())
						)
				.map(this::intersec)
				.map(Set::size)
				.mapToInt(Integer::intValue).sum();
		
	}
	
	private Set<Character> intersec(List<Set<Character>> list) {
		if(list.size() == 1) {
			return list.get(0);
		}
		list.get(list.size()-1).retainAll(intersec(list.subList(0, list.size()-1)));
		return list.get(list.size()-1);
	}

}
