package sml;

/**
 * This class deals with SML "mul" instruction
 *
 * Nasty copy and paste with a couple of lines of modification from add
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 */

public class MulInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

	public MulInstruction(String label, String op) {
		super(label, op);
	}

	public MulInstruction(String label, int result, int op1, int op2) {
		this(label, "mul"); // change this line
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 * value2); // change this line
	}

	@Override
	public String toString() {
		return super.toString() + " times contents of reg " + op1 + " and reg "
				+ op2 + " storing result in reg " + result;
	}
}
