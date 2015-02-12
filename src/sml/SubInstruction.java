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
public class SubInstruction extends ArithmeticInstruction {
	/**
	 * Constructor for the SML "sub" instruction
	 * 
	 * @param label
	 *            the instruction's label
	 * @param result
	 *            the register to store the results of subtract in
	 * @param op1
	 *            register for the number to subtract from
	 * @param op2
	 *            register for the number to subtract
	 */
	public SubInstruction(String label, int result, int op1, int op2) {
		super(label, "sub", result, op1, op2);
	}

	/**
	 * The arithmetic operation involved, here subtraction
	 * 
	 * @param value1
	 *            to be operated on
	 * @param value2
	 *            to be operated on
	 * @return the result of the operation e.
	 */
	@Override
	protected int theOperation(int value1, int value2) {
		return value1 - value2;
	}
}
