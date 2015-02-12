package sml;

/**
 * This class deals with SML "mul" instruction
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class MulInstruction extends ArithmeticInstruction {
	/**
	 * Constructor for the SML "mul" instruction
	 * 
	 * @param label
	 *            the instruction's label
	 * @param result
	 *            the register to store the results of the multiplication in
	 * @param op1
	 *            register for the 1st number to multiply
	 * @param op2
	 *            register for the 2nd number to multiply
	 */
	public MulInstruction(String label, int result, int op1, int op2) {
		super(label, "mul", result, op1, op2);
	}
	
	/**
	 * The arithmetic operation involved, here multiplication
	 * 
	 * @param value1
	 *            to be operated on
	 * @param value2
	 *            to be operated on
	 * @return the result of the operation e.
	 */
	@Override
	protected int theOperation(int value1, int value2) {
		return value1 * value2;
	}
}
