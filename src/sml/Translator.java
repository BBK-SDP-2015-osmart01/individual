package sml;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code
	private String lastWord; // the last word that was parsed (used in bnz
								// instruction).

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	/**
	 * translate reads in the SML program from "fileName" previously stored into
	 * lab (the labels)
	 * 
	 * @author SDP course work code modified by Oliver Smart
	 *         <osmart01@dcs.bbk.ac.uk>
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
	 * return it.
	 * 
	 * Unrecognised instructions handling: currently prints a warning but in the
	 * end should throw an exception?
	 * 
	 * @author SDP course work code modified by Oliver Smart
	 *         <osmart01@dcs.bbk.ac.uk>
	 * @param label
	 *            the label for the instruction
	 * @return the instruction or Null if the line is not recognised.
	 */
	public Instruction getInstruction(String label) {
		if (line.equals(""))
			return null;

		String origLine = line; // for error message
		String ins = scan();
		/*
		 * Possible operands of the instruction
		 * 
		 * they have similar arguments all but bnz have one, two or three
		 * integer arguments so can save repeating same parse code.
		 * 
		 * N.B. we will often scan an integer that is not there resulting for
		 * instance in s2 being set to Integer.MAX_VALUE but this is not a
		 * problem.
		 */
		int r = scanInt();
		int s1 = scanInt();
		int s2 = scanInt();

		// reflection form class name for 'add' the class will be call
		// sml.AddInstruction
		String insClassName = ins.substring(0, 1).toUpperCase()
				+ ins.substring(1); // 'add' to Add
		insClassName = "sml." + insClassName + "Instruction";
		System.out.println("\ndebug got instruction '" + ins
				+ "', examine class " + insClassName);
		try {
			Class<?> insClass = Class.forName(insClassName);

			Constructor<?>[] insConstructors = insClass.getConstructors();
			for (Constructor<?> itConstructor : insConstructors) {
				System.out.println("debug constructor " + itConstructor);
			}
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("exception loading" + ex.getMessage());
		}
		// FOR REFLECTION COMMENT OUT THE SWITCH AND EXPLICIT CALLS
		// switch (ins) {
		// case "add":
		// return new AddInstruction(label, r, s1, s2);
		// case "sub":
		// return new SubInstruction(label, r, s1, s2);
		// case "mul":
		// return new MulInstruction(label, r, s1, s2);
		// case "div":
		// return new DivInstruction(label, r, s1, s2);
		// case "lin":
		// return new LinInstruction(label, r, s1);
		// case "out":
		// return new OutInstruction(label, r);
		// case "bnz":
		// /*
		// * bnz is unusual as the constructor has to be supplied with a
		// * String for nextLabel get this from the lastWord passed (trying to
		// * get s2)
		// */
		// return new BnzInstruction(label, r, lastWord);
		//
		// default:
		// System.out.println("WARNING have unrecognized instruction='" + ins
		// + "' on line '" + label + " " + origLine + "'");
		// }

		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
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

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}
		// store word for possible use in bnz instruction
		lastWord = word;

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}