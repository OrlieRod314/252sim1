/* Implementation of a 32-bit adder in C.
 *
 * Author: Orlando Rodriguez
 */
#include "sim1.h"

void execute_add(Sim1Data *obj) {
	// Want to set temporary carry variables
	int carryIn = 0;
	int carryOut = obj->isSubtraction; // Convenient value, so that it's adopted by carryIn on first iteration

	// Check if a and b are non negative
	// You isolate the MSB, negate it, and isolate it again so you have either 00...01 or 00...00
	obj->aNonNeg = ~((obj->a >> 31) & 0x1) & 0x1;
	obj->bNonNeg = ~((obj->b >> 31) & 0x1) & 0x1;

	for (int i = 0; i < 32; i++) {
        // Old carryOut is now carried in
        carryIn = carryOut;
        // Isolating corresponding bits
        int tempa = (obj->a >> i) & 0x1;
        int tempb = (obj->b >> i) & 0x1;

        // Reversing tempb if it is a subtraction
        if (obj->isSubtraction == 1)
            tempb = ~tempb & 0x1;

        // Updating the right sum value
        if (carryIn ^ (tempa ^ tempb) == 1) // Sum is by default 0x0, so it is not changed if a bit doesn't need to be flipped
            obj->sum |= (0x1 << i); // Have to shift left to align with the correct column

        // Separate carryOut calculation
        int XOR1 = (tempa ^ tempb);
        int AND1 = (XOR1 & carryIn);
        int AND2 = (tempa & tempb);
        carryOut = (AND1 ^ AND2);
	}

    obj->carryOut = carryOut;
    obj->sumNonNeg = ~((obj->sum >> 31) & 0x1) & 0x1;
    obj->overflow = (carryIn ^ carryOut); // Use XOR with carryIn and carryOut of MSB
}
