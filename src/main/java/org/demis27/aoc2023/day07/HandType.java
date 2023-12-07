package org.demis27.aoc2023.day07;

public enum HandType {

    FIVE(6), FOUR(5), FULL_HOUSE(4), THREE(3), TWO_PAIRS(2), PAIR(1), HIGH_CARD(0);

    final int value;

    HandType(int value) {
        this.value = value;
    }
}
