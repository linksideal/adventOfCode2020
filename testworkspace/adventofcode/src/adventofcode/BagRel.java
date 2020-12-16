package adventofcode;

public class BagRel {

	private String bagId;
	private int cardinality;

	public BagRel(String line) {
		if(line.equals("no other")) {
			this.cardinality = 0;
			this.bagId = "no other";
			return;
		}
		this.cardinality = Integer.parseInt(line.substring(0, 1));
		this.bagId = line.substring(2);
	}
	
	public String getBagId() {
		return this.bagId;
	}
	
	public int getCardinality() {
		return this.cardinality;
	}

}
