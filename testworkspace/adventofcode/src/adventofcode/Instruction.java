package adventofcode;

public class Instruction {

	private String operation;
	private int argument;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getArgument() {
		return argument;
	}

	public void setArgument(int argument) {
		this.argument = argument;
	}

	public Instruction(String line) {
		setOperation(line.substring(0, 3));
		setArgument(Integer.parseInt(line.substring(4)));
	}

	public Instruction(Instruction instruction) {
		setOperation(instruction.getOperation());
		setArgument(instruction.getArgument());
	}

	public State run(State state) {
		int accumulator = state.getAccumulator();
		int index = state.getIndex();
		state.getExecutedInstructions().add(index);
		switch (operation) {
		case "acc":
			index++;
			accumulator += argument;
			break;
		case "jmp":
			index += argument;
			break;
		case "nop":
		default:
			index++;
			break;
		}
		return new State(accumulator, index, state.getExecutedInstructions());
	}

	public void change() {
		switch (operation) {
		case "jmp":
			operation = "nop";
			return;
		case "nop":
			operation = "jmp";
			return;
		default:
			return;
		}
	}
}
