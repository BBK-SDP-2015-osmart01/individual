package sml;

/**
 * test "new" class not on sheet. Ends the SML program skipping any subsequence
 * statements.
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class EndInstruction extends Instruction {

	public EndInstruction(String label) {
		super(label, "end");
	}

	/**
	 * Executes an Ends instruction.
	 * 
	 * @Param m the machine to getRegister from.
	 */
	@Override
	public void execute(Machine m) {
		int lastPc = m.getProg().size();
		m.setPc(lastPc + 1); // the next Instruction
	}

	@Override
	public String toString() {
		return super.toString()
				+ " halt the SML program execution on reaching this statement.";
	}
}
