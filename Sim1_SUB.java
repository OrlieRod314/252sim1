/* Simulates a physical device that performs (signed) subtraction on
 * a 32-bit input.
 *
 * Author: Orlando Rodriguez
 */

public class Sim1_SUB {
	public void execute() {
		// REMEMBER: You may call execute() on sub-objects here, and
		//           copy values around - but you MUST NOT create
		//           objects while inside this function.
		
		// Find two's complement of b
		inverter.in = b;
		inverter.execute();
		
		// Addition of a and -b
		adder.a = a;
		adder.b = inverter.out;
		adder.execute();
		sum = adder.sum;
	}



	// --------------------
	// Don't change the following standard variables...
	// --------------------

	// inputs
	public RussWire[] a,b;

	// output
	public RussWire[] sum;

	// --------------------
	// But you should add some *MORE* variables here.
	// --------------------
	private Sim1_2sComplement inverter;
	private Sim1_ADD adder;

	public Sim1_SUB() {
		a = new RussWire[32];
		b = new RussWire[32];
		inverter = new Sim1_2sComplement();
		adder = new Sim1_ADD();
		sum = new RussWire[32];
	}
}

