package sml;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The translator of a SML program
 * 
 * @author SDP course work code modified by Oliver Smart
 *         <osmart01@dcs.bbk.ac.uk>
 *
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	/**
	 * constant parameter list { string, int, int, int }
	 */
	private static final Class<?> PARAMS_SIII[] = { String.class, int.class,
			int.class, int.class };
	/**
	 * constant parameter list { string, int, int }
	 */
	private static final Class<?> PARAMS_SII[] = { String.class, int.class,
			int.class };
	/**
	 * constant parameter list { string, int, string }
	 */
	private static final Class<?> PARAMS_SIS[] = { String.class, int.class,
			String.class };
	/**
	 * constant parameter list { string, int }
	 */
	private static final Class<?> PARAMS_SI[] = { String.class, int.class };

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	/**
	 * translate reads in the SML program from "fileName" previously stored into
	 * lab (the labels)
	 * 
	 * @param lab
	 *            the labels
	 * @param prog
	 *            the program (an ArrayList of Instructions)
	 * @return success indicator - return false if a error is found
	 */
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			// Each iteration processes line and reads the next line into line
			while (sc.hasNextLine()) { // there is another to process
				try {
					line = sc.nextLine();
					line = line.replaceFirst("#.*", ""); // strip comments
															// starting with #
				} catch (NoSuchElementException ioE) {
					System.err.println("ERROR reading from file: '"
							+ ioE.getMessage() + "'");
					return false;
				}

				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("ERROR error opening input file: "
					+ ex.getMessage());
			return false;
		}
		return true; // success!
	}

	/**
	 * When called 'line' should consist of an SML instruction, with its label
	 * already removed. This method translates 'line' into an instruction and
	 * returns it.
	 *
	 * N.B. the method uses reflection to load a appropriate Object for each
	 * instruction. For an instruction 'abc' it will expect to be able to load
	 * class 'sml.AbcInstruction'. This class must have an appropriate
	 * constructor with the same arguments as one of the existing SML
	 * instructions (see coursework pdf document).
	 * 
	 * @param label
	 *            the label for the instruction
	 * @return the instruction or Null if the line is blank
	 * @throws RunTimeException
	 *             if there is any problem found
	 * @author SDP course work code modified by Oliver Smart
	 *         <osmart01@dcs.bbk.ac.uk>
	 */
	public Instruction getInstruction(String label) {
		if (line.equals(""))
			return null;

		String origLine = line; // for error message
		String ins = scan();
		// reflection:
		// first makeup the appropriate class name.
		// For example for 'add' the class is called 'sml.AddInstruction'
		String insClassName = ins.substring(0, 1).toUpperCase()
				+ ins.substring(1); // 'add' to Add
		insClassName = "sml." + insClassName + "Instruction";
		Class<?> insClass = null;
		try {
			insClass = Class.forName(insClassName);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Unrecognized Instruction: '" + ins
					+ "' on line '" + label + origLine + "'"
					+ " (cannot load a Class '" + insClassName + "')");
		}

		// What constructors does this class have?
		Constructor<?> insClassConstructors[] = insClass.getConstructors();
		// Want the constructor with the largest number of parameters
		// (because some classes like 'lin' have "non-operational" constructors)
		Constructor<?> longestConstr = null; 
		int numberOfParams = -1;
		for (Constructor<?> itConstr : insClassConstructors) {
			int itPC = itConstr.getParameterCount();
			System.out.println("debug itPC=" + itPC);
			if (itPC >= numberOfParams) {
				numberOfParams = itPC;
				longestConstr = itConstr;
			}
		}
		System.out.println("debug " + label + origLine + " maxPC=" + numberOfParams);

		// build up parameters to supply to the Constructor
		Object[] params = new Object[numberOfParams];
		params[0] = label;
		Class<?> pTypes[] = longestConstr.getParameterTypes();
		for (int pc=1; pc < numberOfParams; pc++) {
			Class<?> pType = pTypes[pc];
			if (pType.equals(int.class)) {
				int par = scanInt();
				System.out.println("debug int=" + par);
				throwIfNotValid(par, label + origLine);
				params[pc] = par;
			} else if (pType.equals(String.class)) {
				String par = scan();
				System.out.println("debug String=" + par);
				throwIfNotValid(par, label + origLine);
				params[pc] = par;
			} else {
				// ??? throw an error
			}
		}
		
		try {
			return (Instruction) longestConstr.newInstance(params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException ex) {
			// Problem with one of the constructors
			throw new RuntimeException("Constructor Exception  '"
					+ ex.getMessage() + "' class '" + longestConstr
					+ "' for instruction '" + label + origLine + "'");
		}
	}

//		for (Constructor<?> itConstr : insClassConstructors) {
//			// what parameters does this constructor have?
//			Class<?> pTypes[] = itConstr.getParameterTypes();
//
//			try {
//				// look for a constructor with parameters we can provide
//				if (pTypesMatch(pTypes, PARAMS_SIII)) {
//					// the parameters are string,int,int,int. As used in add ..
//					int r = scanInt();
//					int s1 = scanInt();
//					int s2 = scanInt();
//					throwIfNotValid(r, label + origLine);
//					throwIfNotValid(s1, label + origLine);
//					throwIfNotValid(s2, label + origLine);
//					return (Instruction) itConstr.newInstance(label, r, s1, s2);
//
//				} else if (pTypesMatch(pTypes, PARAMS_SII)) {
//					// the parameters are string,int,int. As used in lin
//					int r = scanInt();
//					int x = scanInt();
//					throwIfNotValid(r, label + origLine);
//					throwIfNotValid(x, label + origLine);
//					return (Instruction) itConstr.newInstance(label, r, x);
//
//				} else if (pTypesMatch(pTypes, PARAMS_SIS)) {
//					// the parameters are string,int,string. As used in bnz
//					int s1 = scanInt();
//					String L2 = scan();
//					throwIfNotValid(s1, label + origLine);
//					throwIfNotValid(L2, label + origLine);
//					return (Instruction) itConstr.newInstance(label, s1, L2);
//
//				} else if (pTypesMatch(pTypes, PARAMS_SI)) {
//					// the parameters string,int? (as used in out ....)
//					int s1 = scanInt();
//					throwIfNotValid(s1, label + origLine);
//					return (Instruction) itConstr.newInstance(label, s1);
//				}
//
//			} catch (InstantiationException | IllegalAccessException
//					| IllegalArgumentException | InvocationTargetException ex) {
//				// Problem with one of the constructors
//				throw new RuntimeException("Constructor Exception  '"
//						+ ex.getMessage() + "' class '" + insClassName
//						+ "' for instruction '" + label + origLine + "'");
//			}
//
//		}
//
//		throw new RuntimeException(
//				"No Constructor Found: although there is a class '"
//						+ insClass
//						+ "' cannot find a constructor with appropriate arguments"
//						+ " problem found when dealing with '" + label
//						+ origLine + "'");
//	}

	/**
	 * Check whether two arrays of parameter types have the same elements
	 * 
	 * @param pTypes
	 *            first array of parameter types
	 * @param gTypes
	 *            2nd array of parameter types
	 * @return true if the arrays match, false otherwise
	 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
	 */
	private boolean pTypesMatch(Class<?> pTypes[], Class<?> gTypes[]) {
		boolean retValue = false;
		if (pTypes.length == gTypes.length) {
			retValue = true;
			for (int it = 0; it < pTypes.length; it++) {
				if (!pTypes[it].equals(gTypes[it]))
					retValue = false;
			}
		}
		return retValue;
	}

	/**
	 * Checks that an integer has been successfully parsed from "instruction"
	 * 
	 * 
	 * @param parseInt
	 *            the integer value (if this is Integer.MAX_VALUE there has been
	 *            a problem)
	 * @param instruction
	 *            the instruction that was parsed (used to produce informative
	 *            exception message).
	 * @throws RunTimeException
	 *             if there has been a problem
	 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
	 */
	private void throwIfNotValid(int parseInt, String instruction) {
		if (parseInt == Integer.MAX_VALUE)
			throw new RuntimeException("parse error for '" + instruction
					+ "' failed to get required parameters");
	}

	/**
	 * Checks that a String has been successfully parsed from "instruction"
	 * 
	 * @param parseInt
	 *            the integer value (if this is "" there has been a problem)
	 * @param instruction
	 *            the instruction that was parsed (used to produce informative
	 *            exception message).
	 * @throws RunTimeException
	 *             if there has been a problem
	 * @author Oliver Smart <osmart01@dcs.bbk.ac.uk>
	 */
	private void throwIfNotValid(String parseStr, String instruction) {
		if (parseStr.equals("")) // use the int version to do the throw
			throwIfNotValid(Integer.MAX_VALUE, instruction);
	}

	/**
	 * Return the first word of line and remove it from line.
	 * 
	 * @return the first word of line or "" if there is no word.
	 * @author SDP course work code (unmodified)
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	/**
	 * Return the first word of line as an integer and remove it from line.
	 * 
	 * @return the integer value of the first work or Integer.MAX_VALUE if there
	 *         is an error
	 * @author SDP course work code (unmodified)
	 */
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}
		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}