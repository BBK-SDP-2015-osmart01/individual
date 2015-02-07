package sml;

import java.util.ArrayList;

import lombok.Data;

/*
 * The machine language interpreter
 */
@Data
public class Machine {
	// The labels in the SML program, in the order in which
	// they appear (are defined) in the program

	private Labels labels;

	// The SML program, consisting of prog.size() instructions, each
	// of class Instruction (or one of its subclasses)
	private ArrayList<Instruction> prog;

	// The registers of the SML machine
	private Registers registers;

	// The program counter; it contains the index (in prog) of
	// the next instruction to be executed.

	private int pc;

	{
		labels = new Labels();
		prog = new ArrayList<>();
		pc = 0;
	}

	/**
	 * The SML interpreter must be supplied with a single SML format input file
	 * as an argument
	 * 
	 * @param args
	 *            command line arguments, will stop with error/usage
	 *            statement unless there is a single input file name provided
	 */
	public static void main(String[] args) {
		if (args.length != 1) { // check that user has provided the required
								// single argument
			System.err.println("ERROR found in SML interpreter");
			System.err
					.println("ERROR you have not provided the filename to be processed");
			System.err.println("usage:");
			System.err.println("\tjava ... Machine file.sml");
			System.err
					.println("Where file.sml is the input SML program file that must exist in the src directory.");
			System.exit(1); // stop with error
		}
		Machine m = new Machine();
		Translator t = new Translator(args[0]);
		t.readAndTranslate(m.getLabels(), m.getProg());

		System.out.println("Here is the program; it has " + m.getProg().size()
				+ " instructions.");
		System.out.println(m);

		System.out.println("Beginning program execution.");
		m.execute();
		System.out.println("Ending program execution.");

		System.out.println("Values of registers at program termination:");
		System.out.println(m.getRegisters() + ".");
	}

	// Print the program

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i != getProg().size(); i++)
			s.append(getProg().get(i) + "\n");
		return s.toString();
	}

	// Execute the program in prog, beginning at instruction 0.
	// Precondition: the program and its labels have been store properly.

	public void execute() {
		setPc(0);
		setRegisters(new Registers());
		while (getPc() < getProg().size()) {
			Instruction ins = getProg().get(getPc());
			setPc(getPc() + 1);
			ins.execute(this);
		}
	}
}