package sml;

/**
 * This class deals with SML "mul" instruction
 *
 * Nasty copy and paste with a couple of lines of modification from add
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 */
public class MulInstruction extends ArithmeticInstruction {
	public MulInstruction(String label, int result, int op1, int op2) {
		super(label, "mul", result, op1, op2);
	}
}
