package sml;

/**
 * To avoid large code duplication in AddInstruction, SubInstruction ... classes
 * use this intermediate abstract class that does all the hard work. The only
 * thing that the daughter classes need is a constructor that provides the
 * appropriate opcode to implement theOperation( int, int) method.
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
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		int valueResult = theOperation(value1, value2);
		m.getRegisters().setRegister(result, valueResult);
	}

	/**
	 * The arithmetic operation defined in the sub class.
	 * 
	 * @param value1
	 *            to be operated on
	 * @param value2
	 *            to be operated on
	 * @return the result of the operation e.g., for add value1 + value2
	 */
	protected abstract int theOperation(int value1, int value2);

	@Override
	public String toString() {
		return super.toString() + " contents of reg " + op1 + " and reg " + op2
				+ " storing result in reg " + result;
	}

}
