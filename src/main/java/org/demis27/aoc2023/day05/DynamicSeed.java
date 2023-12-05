package org.demis27.aoc2023.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DynamicSeed {

    private long value;

    private long range;

    public static List<DynamicSeed> read(String line) {
        List<DynamicSeed> seeds = new ArrayList<>();
        String subLine = line.substring(7);
        List<Long> elements = Arrays.stream(subLine.split(" ")).map(Long::parseLong).toList();
        for (int p = 0; p < elements.size(); p+=2) {
            seeds.add(new DynamicSeed(elements.get(p), elements.get(p+1)));
        }
        return seeds;

    }

    @Override
    public String toString() {
        return "DynamicSeed{" +
                "value=" + value +
                ", range=" + range +
                '}';
    }

}
