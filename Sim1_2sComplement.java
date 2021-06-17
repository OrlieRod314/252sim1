/* Simulates a physical device that performs 2's complement on a 32-bit input.
 *
 * Author: Orlando Rodriguez
 */

public class Sim1_2sComplement
{
	public void execute()
	{
		// TODO: fill this in!
		//
		// REMEMBER: You may call execute() on sub-objects here, and
		//           copy values around - but you MUST NOT create
		//           objects while inside this function.
		for (int i = 0; i < 31; i++) {
			NOTGates[i].in.set(in[i].get());
			NOTGates[i].execute();
			out[i].set(NOTGates[i].out.get());
		}
		
		ADDOne.a = out;
		ADDOne.execute();
		out = ADDOne.sum;
	}



	// you shouldn't change these standard variables...
	public RussWire[] in;
	public RussWire[] out;


	// TODO: add some more variables here.  You must create them
	//       during the constructor below.  REMEMBER: You're not
	//       allowed to create any object inside the execute()
	//       method above!
	
	private Sim1_NOT[] NOTGates;
	private Sim1_ADD ADDOne;


	public Sim1_2sComplement()
	{
		// TODO: this is where you create the objects that
		//       you declared up above.
		in = new RussWire[32];
		out = new RussWire[32];
		NOTGates = new Sim1_NOT[32];
		ADDOne = new Sim1_ADD();
		for (RussWire wire : ADDOne.b)
			wire.set(false);
		ADDOne.b[31].set(true);

	}
}

