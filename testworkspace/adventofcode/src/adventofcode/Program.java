package adventofcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

	private List<Instruction> instructions;
	private State state;

	private final State STARTING_STATE = new State(0, 0, new HashSet<Integer>());

	public Program(List<String> lines) {
		this.state = STARTING_STATE;
		this.instructions = lines.stream().map(Instruction::new).collect(Collectors.toList());
	}

	public Program(Program program) {
		this.state = STARTING_STATE;
		this.instructions = new ArrayList<>(clone(program.getInstructions()));
	}
	
	private List<Instruction> clone(List<Instruction> instructionsToClone){
		return instructionsToClone.stream().map(Instruction::new).collect(Collectors.toList());
	}

	public void run() {
		while (!state.getExecutedInstructions().contains(state.getIndex()) && state.getIndex() < instructions.size()) {
			state = instructions.get(state.getIndex()).run(state);
		}
	}

	public int getAccumulator() {
		return state.getAccumulator();
	}

	public boolean terminatedNormally() {
		return state.getIndex() >= instructions.size();
	}

	public void changeInstruction(int i) {
		instructions.get(i).change();
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
}
