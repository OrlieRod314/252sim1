/* Implementation of a 32-bit adder in C.
 *
 * Author: Orlando Rodriguez
 */
#include "sim1.h"

void execute_add(Sim1Data *obj)
{
	// TODO: implement me!
	int carryIn = 0;
	int carryOut = obj->isSubtraction;

	obj->aNonNeg = ~((obj->a >> 31) & 0x1) & 0x1;
	obj->bNonNeg = ~((obj->b >> 31) & 0x1) & 0x1;

	for (int i = 0; i < 32; i++) {
        carryIn = carryOut;
        int tempa = (obj->a >> i) & 0x1;
        int tempb = (obj->b >> i) & 0x1;
        if (obj->isSubtraction == 1)
            tempb = ~tempb & 0x1;
        if (carryIn ^ (tempa ^ tempb) == 1)
            obj->sum |= (0x1 << i);

        int XOR1 = (tempa ^ tempb);
        int AND1 = (XOR1 & carryIn);
        int AND2 = (tempa & tempb);
        carryOut = (AND1 ^ AND2);
	}

    obj->carryOut = carryOut;
    obj->sumNonNeg = ~((obj->sum >> 31) & 0x1) & 0x1;
    obj->overflow = (carryIn ^ carryOut);
}
