package sml;

/**
 * This class deals with SML "bnz" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 bnz s1 L2" is an instruction to "If the contents of register s1 is not
 * zero, then make the statement labeled L2 the next one to execute"
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class BnzInstruction extends Instruction {
	private int register;
	private String nextLabel; // label to jump to

	public BnzInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public BnzInstruction(String label, int register, String nextLabel) {
		super(label, "bnz");
		this.register = register;
		this.nextLabel = nextLabel;
	}

	/**
	 * Executes and out instruction.
	 * 
	 * @Param m the machine to getRegister from.
	 */
	@Override
	public void execute(Machine m) {
		// get the value of the stored register from the machine
		int value = m.getRegisters().getRegister(register);
		if (value != 0) {
			System.out.println("debug BnzInstruction need to make "
					+ "the statement label '" + nextLabel
					+ "' the next to execute - not yet written");
		}
	}

	@Override
	public String toString() {
		return super.toString() + " if contents of " + register
				+ " is not zero then " + nextLabel
				+ " will be next label to execute";
	}
}
