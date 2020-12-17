package adventofcode;

import java.util.Set;

public class State {

	private int accumulator;
	private int index;
	private Set<Integer> executedInstructions;
	
	public State(int accumulator, int index, Set<Integer> executedInstructions) {
		setAccumulator(accumulator);
		setIndex(index);
		setExecutedInstructions(executedInstructions);
	}

	public int getAccumulator() {
		return accumulator;
	}

	public void setAccumulator(int accumulator) {
		this.accumulator = accumulator;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Set<Integer> getExecutedInstructions() {
		return executedInstructions;
	}

	public void setExecutedInstructions(Set<Integer> executedInstructions) {
		this.executedInstructions = executedInstructions;
	}
	
}
