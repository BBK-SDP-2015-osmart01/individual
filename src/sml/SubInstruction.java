package sml;

/**
 * This class deals with SML "sub" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 sub r s1 s2" is an instruction to
 * "Subtract the contents of register S2 from the contents of s1 and store the result in register r"
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 */

public class SubInstruction extends Instruction {

	protected int result;
	protected int op1;
	protected int op2;

	public SubInstruction(String label, String op) {
		super(label, op);
	}

	public SubInstruction(String label, int result, int op1, int op2) {
		this(label, "sub");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 - value2);
	}

	@Override
	public String toString() {
		return super.toString() + " subtract the contents of reg " + op2
				+ " from reg " + op1 + " storing result in reg " + result;
	}
}
