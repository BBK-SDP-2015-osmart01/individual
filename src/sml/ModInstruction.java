package sml;

/**
 * test "new" class not on sheet. Works out modulus aka remainder.
 *  
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class ModInstruction extends ArithmeticInstruction {
	/**
	 * Constructor for the SML "mod" instruction
	 * 
	 * @param label
	 *            the instruction's label
	 * @param result
	 *            the register to store the resulting remainder 
	 * @param op1
	 *            register for the number to be divided
	 * @param op2
	 *            register for the number to divide by
	 */
	public ModInstruction(String label, int result, int op1, int op2) {
		super(label, "mod", result, op1, op2);
	}

	/**
	 * The arithmetic operation involved, here %
	 * 
	 * @param value1
	 *            to be operated on
	 * @param value2
	 *            to be operated on
	 * @return the result of the operation.
	 */
	@Override
	protected int theOperation(int value1, int value2) {
		return value1 % value2;
	}
}
