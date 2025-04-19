#include "queen_attack.h"

static int is_invalid_pos(position_t queen) {
    return queen.column > 7 || queen.row > 7;
}

attack_status_t can_attack(position_t queen_1, position_t queen_2) {
    if (is_invalid_pos(queen_1) || is_invalid_pos(queen_2)) {
        return INVALID_POSITION;
    }

    if (queen_1.row == queen_2.row && queen_1.column == queen_2.column) {
        return INVALID_POSITION;
    }

    if(queen_1.row == queen_2.row || queen_1.column == queen_2.column) {
        return CAN_ATTACK;
    } 

    float axisy = queen_1.column - queen_2.column;

    if (axisy == queen_1.row - queen_2.row || axisy == queen_2.row - queen_1.row) {
        return CAN_ATTACK;
    }

    return CAN_NOT_ATTACK;
}


// attack_status_t can_attack(position_t queen_1, position_t queen_2) {
//     if (queen_1.row > 7 || queen_1.column > 7 || queen_2.column > 7 || queen_2.row > 7) {
//         return INVALID_POSITION;
//     }

//     if (queen_1.row == queen_2.row && queen_1.column == queen_2.column) {
//         return INVALID_POSITION;
//     }

//     // Na mesma coluna ou linha, pode atacar
//     if(queen_1.row == queen_2.row || queen_1.column == queen_2.column) {
//         return CAN_ATTACK;
//     } 

//     // Ref: https://stackoverflow.com/questions/41432956/checking-for-horizontal-vertical-and-diagonal-pairs-given-coordinates
//     // Pra saber se esta na mesma diagonal, eu posso 
//     // verificar a diagonal positiva: y2 - y1 == x2 - x1
//     if (queen_1.column - queen_2.column == queen_1.row - queen_2.row) {
//         return CAN_ATTACK;
//     }

//     // e testar a diagonal negativa: y2 - y1 == x1 - x2
//     if (queen_1.column - queen_2.column == queen_2.row - queen_1.row) {
//         return CAN_ATTACK;
//     }

//     return CAN_NOT_ATTACK;
// }
