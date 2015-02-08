package sml;

/**
 * This class deals with SML "out" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 out s1" is an instruction to
 * "Print the contents of register s1 onto the Java console (using println)"
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class OutInstruction extends Instruction {
	private int register;

	public OutInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public OutInstruction(String label, int register) {
		super(label, "out");
		this.register = register;
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
		// and print it
		System.out.println(value);
	}

	@Override
	public String toString() {
		return super.toString() + " print register " + register
				+ " contents to Java console ";
	}
}
