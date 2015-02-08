package sml;

public class SubInstruction extends AddInstruction{

	public SubInstruction(String label, int result, int op1, int op2) {
		super(label, result, op1, op2);
	}
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 - value2);
	}

	@Override
	public String toString() {
		// not allowed to access super.super so
		String id = label + ": " + opcode;
		return id + " subtract the contents of reg " + op2 + " from reg "
				+ op1 + " storing result in reg " + result;
	}

}
