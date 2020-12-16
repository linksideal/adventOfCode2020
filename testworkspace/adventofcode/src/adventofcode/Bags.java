package adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bags {

	private Map<String, Bag> bagMap;

	public Bags(List<String> lines) {
		bagMap = new HashMap<>();
		for (String line : lines) {
			addLineToBags(line);
		}
	}

	public Map<String, Bag> getBagMap() {
		return bagMap;
	}

	private void addLineToBags(String line) {
		Bag bag = new Bag(line);
		bagMap.put(bag.getId(), bag);
	}

	public int numberOfBagsThatCanContainShinyGoldBag() {
		return (int) bagMap.values().stream().filter(this::bagContainsShinyGold).count();
	}

	private boolean bagContainsShinyGold(Bag bag) {
		return bag.getBagRels().stream()
				.anyMatch(bagRel -> (bagIsShinyGold(bagRel.getBagId()) || bagContainsShinyGold(bagRel.getBagId())));
	}

	private boolean bagContainsShinyGold(String bagId) {
		return bagMap.containsKey(bagId) && bagContainsShinyGold(bagMap.get(bagId));
	}

	private boolean bagIsShinyGold(String bagId) {
		return bagId.equals("shiny gold");
	}

	public int numberOfBagsInsideBag(String bagId) {
		return bagMap.getOrDefault(bagId, new Bag()).getBagRels().stream()
				.mapToInt(bagRel -> bagRel.getCardinality() * (1 + numberOfBagsInsideBag(bagRel.getBagId()))).sum();
	}
}
