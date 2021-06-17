/* Simulates a physical device that performs (signed) addition on
 * a 32-bit input.
 *
 * Author: Orlando Rodriguez
 */

public class Sim1_ADD {

	public void execute() {


		for (int i = 31; i >= 0; i--) {
			if (i != 31)
				colCarryIn[i].set(colCarryOut[i + 1].get());

			//Result of addition
			outXOR1[i].a.set(a[i].get());
			outXOR1[i].b.set(b[i].get());
			outXOR1[i].execute();

			outXOR2[i].a.set(colCarryIn[i].get());
			outXOR2[i].b.set(outXOR1[i].out.get());
			outXOR2[i].execute();
			
			sum[i].set(outXOR2[i].out.get());
			
			//Carry out
			carryXOR1[i].a.set(a[i].get());
			carryXOR1[i].b.set(b[i].get());
			carryXOR1[i].execute();
			
			carryAND1[i].a.set(carryXOR1[i].out.get());
			carryAND1[i].b.set(colCarryIn[i].get());
			carryAND1[i].execute();
			
			carryAND2[i].a.set(a[i].get());
			carryAND2[i].b.set(b[i].get());
			carryAND2[i].execute();
			
			carryXOR2[i].a.set(carryAND2[i].out.get());
			carryXOR2[i].b.set(carryAND1[i].out.get());
			carryXOR2[i].execute();
			
			colCarryOut[i].set(carryXOR2[i].out.get());
		}

		// If Carry in and Carry out for MSB column are different, then 
		// overflow occurred. 
		// Carry in/out can only differ if a and b are the same sign.
		// Overflow can only occur if a and b are the same sign.
		carryOut.set(colCarryOut[0].get());
		overflowXOR1.a.set(colCarryIn[0].get());
		overflowXOR1.b.set(colCarryOut[0].get());
		overflowXOR1.execute();
		overflow.set(overflowXOR1.out.get());
	}



	// ------ 
	// It should not be necessary to change anything below this line,
	// although I'm not making a formal requirement that you cannot.
	// ------ 

	// inputs
	public RussWire[] a,b;

	// outputs
	public RussWire[] sum;
	public RussWire   carryOut, overflow;
	
	// logic gates
	
	// out gates
	private Sim1_XOR outXOR1[];
	private Sim1_XOR outXOR2[];
	
	// carry gates
	private Sim1_XOR carryXOR1[];
	private Sim1_XOR carryXOR2[];
	private Sim1_AND carryAND1[];
	private Sim1_AND carryAND2[];
	
	//overflow gates
	private Sim1_XOR overflowXOR1;
	
	//temp variables
	private RussWire colCarryOut[];
	private RussWire colCarryIn[];

	public Sim1_ADD()
	{
		/* Instructor's Note:
		 *
		 * In Java, to allocate an array of objects, you need two
		 * steps: you first allocate the array (which is full of null
		 * references), and then a loop which allocates a whole bunch
		 * of individual objects (one at a time), and stores those
		 * objects into the slots of the array.
		 */

		a   = new RussWire[32];
		b   = new RussWire[32];
		sum = new RussWire[32];

		for (int i=0; i<32; i++)
		{
			a  [i] = new RussWire();
			b  [i] = new RussWire();
			sum[i] = new RussWire();
		}

		carryOut = new RussWire();
		carryOut.set(false);
		overflow = new RussWire();
		
		// logic gates
		
		// out gates
		outXOR1 = new Sim1_XOR[32];
		outXOR2 = new Sim1_XOR[32];
		
		// carry gates
		carryXOR1 = new Sim1_XOR[32];
		carryXOR2 = new Sim1_XOR[32];
		carryAND1 = new Sim1_AND[32];
		carryAND2 = new Sim1_AND[32];
		
		//overflow gates
		overflowXOR1 = new Sim1_XOR();
		
		for (int i = 0; i < 32; i++) {
			// out gates
			outXOR1[i] = new Sim1_XOR();
			outXOR2[i] = new Sim1_XOR();
			
			// carry gates
			carryXOR1[i] = new Sim1_XOR();
			carryXOR2[i] = new Sim1_XOR();
			carryAND1[i] = new Sim1_AND();
			carryAND2[i] = new Sim1_AND();
		}
		
		//temp variables
		colCarryIn = new RussWire[32];
		colCarryOut = new RussWire[32];
		
		for (int i = 0; i < 32; i++) {
			colCarryIn[i] = new RussWire();
			colCarryOut[i] = new RussWire();
		}
		
		colCarryIn[0].set(false);
		
	}
}

