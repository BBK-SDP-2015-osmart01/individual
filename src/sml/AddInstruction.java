package sml;

/**
 * This class deals with SML "add" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 add r s1 s2" is an instruction to
 * "Add the contents of register s1 and s2 and store the result in register r"
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 */

public class AddInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

	public AddInstruction(String label, String op) {
		super(label, op);
	}

	public AddInstruction(String label, int result, int op1, int op2) {
		this(label, "add");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 + value2);
	}

	@Override
	public String toString() {
		return super.toString() + " add contents of reg " + op1 + " and reg "
				+ op2 + " storing result in reg " + result;
	}
}
