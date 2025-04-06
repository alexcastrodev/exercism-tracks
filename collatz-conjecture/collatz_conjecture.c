#include "collatz_conjecture.h"

int steps(int start) {
    if (start < 1)
        return ERROR_VALUE;
    
    int result = 0;
    while (start != 1) {
        // Bitwise AND
        if (!(start & 1)) {
            // Shift right
            start = start >> 1;
        } else {
            start = (start * 3) + 1;
        }

        result += 1;
    }

    return result;
}
