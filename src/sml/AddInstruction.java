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
public class AddInstruction extends ArithmeticInstruction {
	public AddInstruction(String label, int result, int op1, int op2) {
		super(label, "add", result, op1, op2);
	}
}
