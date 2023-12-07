package org.demis27.aoc2023.day07;

import java.util.Arrays;

public enum Card implements Comparable<Card>{

    ACE('A', 14), KING('K', 13), QUEEN('Q', 12), JACK('J', 11), TEN('T', 10),
    NINE('9', 9), EIGHT('8', 8), SEVEN('7', 7), SIX('6', 6), FIVE('5', 5),
    FOUR('4', 4), THREE('3', 3), TWO('2', 2), JOKER('J', 1);

    final char type;
    final int rank;

    Card(char type, int rank) {
        this.type = type;
        this.rank = rank;
    }

    public static Card convert(char value, boolean withJokers) {
        return Arrays.stream(values())
                .filter(c -> (!withJokers && c != JOKER) || (withJokers && c != JACK))
                .filter(c -> c.type == value)
                .findFirst().orElse(null);
    }

    public static Card[] convert(char[] values, boolean joker) {
        Card[] result = new Card[5];
        for (int i = 0; i < values.length; i++) {
            result[i] = convert(values[i], joker);
        }
        return result;
    }
}
