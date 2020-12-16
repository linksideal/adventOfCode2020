package adventofcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {

	private List<BagRel> bagRels;
	private String id;

	public Bag(String line) {
		this.id = line.split("contain")[0].replace("bags", "").trim();
		this.bagRels = Arrays
				.asList(line.split("contain")[1].split(",")).stream().map(string -> string.replace(".", "")
						.replace("bags", "").replace("bag", "").trim()).map(s -> new BagRel(s)).collect(Collectors.toList());
	}
	
	public Bag() {
		this.id = "";
		this.bagRels = Collections.emptyList();
	}

	// GETTER
	public String getId() {
		return this.id;
	}

	public List<BagRel> getBagRels() {
		return this.bagRels;
	}

}
