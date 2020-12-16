package adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {

	private List<String> validChildBagIDs;
	private String id;

	public Bag(String id, List<String> validChildBagIDs) {
		this.id = id;
		this.validChildBagIDs = validChildBagIDs;
	}

	public Bag(String line) {
		this.id = line.split("contain")[0].replace("bags", "").trim();
		this.validChildBagIDs = Arrays
				.asList(line.split("contain")[1].split(",")).stream().map(string -> string.replace(".", "")
						.replace("bags", "").replace("bag", "").replaceAll("[0-9] ", "").trim())
				.collect(Collectors.toList());
	}

	// GETTER
	public String getId() {
		return this.id;
	}

	public List<String> getValidChildBagIDs() {
		return this.validChildBagIDs;
	}

	// SETTER
	public void setValidChildBagIDs(List<String> validChildBagIDs) {
		this.validChildBagIDs = validChildBagIDs;
	}

}
