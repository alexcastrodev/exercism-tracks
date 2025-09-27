#include "grains.h"

// ========================
// Segunda tentativa
// ========================
uint64_t square(uint8_t index) {
    if (index < 1 || index > 64) {
        return 0;
    } 

    // Deslocamento de bits calcula potências de 2, e (index - 1) garante que o cálculo esteja alinhado com a numeração das casas.
    return (uint64_t)1 << (index - 1);
}

// ========================
// Primeira tentativa
// ========================
// uint64_t square(uint8_t index) {
//     if (index == 0 || index == 1 || index == 2) {
//         return index;
//     }

//     uint64_t last_result = 2;

//     for(int i = 3; i <= index; i++) {
//         last_result = last_result * 2;
//     }

//     return last_result;
// }

uint64_t total(void) {
    return (square(64) * 2) - 1;
}
