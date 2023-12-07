package org.demis27.aoc2023.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day07 {
    public long processPartOne(String s) throws IOException {
        List<Hand> hands = readHands(s, false);
        hands.sort(Hand::compareTo);
        long result = 0;
        for (int i = 0; i < hands.size(); i++) {
            System.out.println(hands.get(i));
            result += (i + 1) * hands.get(i).getBid();
        }
        return result;
    }

    public long processPartTwo(String s) throws IOException {
        List<Hand> hands = readHands(s, true);
        hands.sort(Hand::compareTo);
        long result = 0;
        for (int i = 0; i < hands.size(); i++) {
            System.out.println(hands.get(i));
            result += (i + 1) * hands.get(i).getBid();
        }
        return result;
    }

    private List<Hand> readHands(final String filename, boolean withJokers) throws IOException {
        List<Hand> hands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                hands.add(new Hand(line, withJokers));
                line = reader.readLine();
            }
        }
        return hands;
    }

}
