package org.demis27.aoc2023.days.day07;

import org.demis27.aoc2023.days.day07.Card;
import org.demis27.aoc2023.days.day07.Day07;
import org.demis27.aoc2023.days.day07.Hand;
import org.demis27.aoc2023.days.day07.HandType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day07Test {

    @Test
    void detectValue() {
        Hand hand = new Hand("32T3K 765", false);
        Assertions.assertEquals(765, hand.getBid());
        Assertions.assertEquals(Card.THREE, hand.getCards()[0]);
        Assertions.assertEquals(Card.TWO, hand.getCards()[1]);
        Assertions.assertEquals(Card.TEN, hand.getCards()[2]);
        Assertions.assertEquals(Card.THREE, hand.getCards()[3]);
        Assertions.assertEquals(Card.KING, hand.getCards()[4]);

        Assertions.assertEquals(HandType.PAIR, hand.getHandType());

        hand = new Hand("QKQKQ 765", false);
        Assertions.assertEquals(HandType.FULL_HOUSE, hand.getHandType());
    }

    @Test
    void sort() {
        Hand hand1 = new Hand("KQKQK 765", false);
        Hand hand2 = new Hand("33333 765", false);
        Hand hand3 = new Hand("22T22 765", false);

        List<Hand> hands = List.of(hand1, hand2, hand3).stream().collect(Collectors.toList());

        hands.sort(Hand::compareTo);
        hands.get(0).equals(hand2);
        hands.get(1).equals(hand1);
        hands.get(2).equals(hand3);
    }


    @Test
    void day07_part1_sample() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartOne("day07-part1-sample.txt");
        Assertions.assertEquals(6440, result);
    }

    @Test
    void day07_part2_sample() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartTwo("day07-part2-sample.txt");
        Assertions.assertEquals(5905, result);
    }
    @Test
    void day07_part1() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartOne("day07.txt");
        Assertions.assertEquals(254024898, result);
    }

    @Test
    void day07_part2() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartTwo("day07.txt");
        Assertions.assertEquals(254115617, result);
    }

}
