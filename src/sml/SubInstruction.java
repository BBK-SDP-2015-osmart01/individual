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
	public SubInstruction(String label, int result, int op1, int op2) {
		super(label, "sub", result, op1, op2);
	}
}
