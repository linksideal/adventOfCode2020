package adventofcode;

import java.util.Optional;

public class ProgramOptimizer {

	private ProgramOptimizer() {
	}

	public static Optional<Program> optimize(Program program) {
		int i = 0;
		Program changedProgram = new Program(program);
		changedProgram.run();
		while (!changedProgram.terminatedNormally() && i < program.getInstructions().size()) {
			changedProgram = new Program(program);
			changedProgram.changeInstruction(i);
			changedProgram.run();
			i++;
		}
		if( changedProgram.terminatedNormally()) {
			return Optional.of(changedProgram);
		}
		return Optional.empty();
	}
}
