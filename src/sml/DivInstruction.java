package sml;

/**
 * This class deals with SML "add" instruction
 * 
 * According to assignment sheet:
 * 
 * "L1 div r s1 s2" is an instruction to "Divide (Java integer division) the
 * contents of register s1 by the contents of register s2 and store the result
 * in register r"
 * 
 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
 */
public class DivInstruction extends ArithmeticInstruction {
	/**
	 * Constructor for the SML "div" instruction
	 * 
	 * @param label
	 *            the instruction's label
	 * @param result
	 *            the register to store the results of division in
	 * @param op1
	 *            register for the number to be divided
	 * @param op2
	 *            register for the the number to divide by
	 */
	public DivInstruction(String label, int result, int op1, int op2) {
		super(label, "div", result, op1, op2);
	}
}
