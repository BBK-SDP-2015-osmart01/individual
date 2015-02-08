package sml;

/**
 * Attempt to avoid large code duplication in AddInstruction,
 * SubInstruction ...
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 */

public abstract class ArithmeticInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

	public ArithmeticInstruction(String label, String opcode, int result,
			int op1, int op2) {
		super(label, opcode);
		if (opcode.equals("add") || opcode.equals("sub")
				|| opcode.equals("mul") || opcode.equals("div")) {
			this.result = result;
			this.op1 = op1;
			this.op2 = op2;
		} else {
			throw new RuntimeException("unrecognized opcode "+ opcode);
		}
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		int valueResult = Integer.MAX_VALUE;
		if (opcode.equals("add")) {
			valueResult = value1 + value2;
		} else if (opcode.equals("sub")) {
			valueResult = value1 - value2;
		} else if (opcode.equals("mul")) {
			valueResult = value1 * value2;
		} else if (opcode.equals("mul")) {
			valueResult = value1 / value2;
		}
		m.getRegisters().setRegister(result, valueResult);
	}

	@Override
	public String toString() {
		return super.toString() + " contents of reg " + op1 + " and reg " + op2
				+ " storing result in reg " + result;
	}

}
