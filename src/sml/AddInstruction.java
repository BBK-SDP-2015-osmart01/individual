package sml;

/**
 * This class deals with SML "add" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 add r s1 s2" is an instruction to
 * "Add the contents of register s1 and s2 and store the result in register r"
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class AddInstruction extends ArithmeticInstruction {
	/**
	 * Constructor for the SML "add" instruction
	 * 
	 * @param label
	 *            the instruction's label
	 * @param result
	 *            the register to store the results of the addition in
	 * @param op1
	 *            register for the 1st number to add
	 * @param op2
	 *            register for the 2nd number to add
	 */
	public AddInstruction(String label, int result, int op1, int op2) {
		super(label, "add", result, op1, op2);
	}

	/**
	 * The arithmetic operation involved, here addition
	 * 
	 * @param value1
	 *            to be operated on
	 * @param value2
	 *            to be operated on
	 * @return the result of the operation e.
	 */
	@Override
	protected int theOperation(int value1, int value2) {
		return value1 + value2;
	}
}
