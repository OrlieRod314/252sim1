/* Simulates a physical device that performs (signed) addition on
 * a 32-bit input.
 *
 * Author: Orlando Rodriguez
 */

public class Sim1_ADD {

	public void execute() {


		for (int i = 31; i >= 0; i--) {
			carryIn.set(carryOut.get());

			//Result of addition
			outXOR1.a.set(a[i].get());
			outXOR1.b.set(b[i].get());
			outXOR1.execute();

			outXOR2.a.set(carryIn.get());
			outXOR2.b.set(outXOR1.out.get());
			outXOR2.execute();
			
			sum[i].set(outXOR2.out.get());
			
			//Carry out
			carryXOR1.a.set(a[i].get());
			carryXOR1.b.set(b[i].get());
			carryXOR1.execute();
			
			carryAND1.a.set(carryXOR1.out.get());
			carryAND1.b.set(carryIn.get());
			carryAND1.execute();
			
			carryAND2.a.set(a[i].get());
			carryAND2.b.set(b[i].get());
			carryAND2.execute();
			
			carryXOR2.a.set(carryAND2.out.get());
			carryXOR2.b.set(carryAND1.out.get());
			carryXOR2.execute();
			
			carryOut.set(carryXOR2.out.get());
		}

		// If Carry in and Carry out for MSB column are different, then 
		// overflow occurred. 
		// Carry in/out can only differ if a and b are the same sign.
		// Overflow can only occur if a and b are the same sign.
		overflowXOR1.a.set(carryIn.get());
		overflowXOR1.b.set(carryOut.get());
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
	private Sim1_XOR outXOR1;
	private Sim1_XOR outXOR2;
	
	// carry gates
	private Sim1_XOR carryXOR1;
	private Sim1_XOR carryXOR2;
	private Sim1_AND carryAND1;
	private Sim1_AND carryAND2;
	
	//overflow gates
	private Sim1_XOR overflowXOR1;
	
	//temp variables
	private RussWire carryIn;

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
		outXOR1 = new Sim1_XOR();
		outXOR2 = new Sim1_XOR();
		
		// carry gates
		carryXOR1 = new Sim1_XOR();
		carryXOR2 = new Sim1_XOR();
		carryAND1 = new Sim1_AND();
		carryAND2 = new Sim1_AND();
		
		//overflow gates
		overflowXOR1 = new Sim1_XOR();
		
		//temp variables
		carryIn = new RussWire();
		carryIn.set(false);
		
	}
}

