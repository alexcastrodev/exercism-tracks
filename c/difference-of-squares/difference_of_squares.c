#include "difference_of_squares.h"


// Soma dos quadrados dos n primeiros naturais
// sn = n . (n+1) . (2n+1) / 6
unsigned int sum_of_squares(unsigned int number) {
    return (number * (number + 1) * (2 * number + 1) / 6);
}

// Progressãso aritmética
// sn = a1 + an * n / 2
unsigned int square_of_sum(unsigned int number) {
    int total = (number * (number + 1) / 2);
    return total * total;
}


// First code
// unsigned int sum_of_squares(unsigned int number) {
//     int total = 0;

//     while (number > 0) {
//         total = total + (number * number);
//         number = number - 1;
//     }

//     return total;
// }

// unsigned int square_of_sum(unsigned int number) {
//     int total = 0;
    
//     while (number > 0) {
//         total = total + number;
//         number = number - 1;
//     }

//     return total * total;
// }

unsigned int difference_of_squares(unsigned int number) {
    return square_of_sum(number) - sum_of_squares(number);
}
