package org.demis27.aoc2023.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Seed {

    private long value;

    public static List<Seed> readSeeds(final String line) {
        String subLine = line.substring(7);
        String[] split = subLine.split(" ");
        return Arrays.stream(split).map(String::trim).filter(s -> !s.isEmpty()).map(s -> new Seed(Long.parseLong(s))).toList();
    }

    @Override
    public String toString() {
        return "Seed{" +
                "value=" + value +
                '}';
    }
}
