package org.demis27.aoc2023.day04;

import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Day04 {
    public long processPartOne(String s) throws IOException {
        return process(s).getValue0();
    }

    public long processPartTwo(String s) throws IOException {
        return process(s).getValue1();
    }

    private Pair<Long, Long> process(final String filename) throws IOException {
        long result = 0;
        List<Long> result2 = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split("[:|]");

                List<Integer> winingNumbers = Arrays.stream(split[1].split(" ")).filter(n -> !n.isEmpty()).map(n -> n.trim()).map(n -> Integer.parseInt(n)).toList();
                List<Integer> numbers = Arrays.stream(split[2].split(" ")).map(n -> n.trim()).filter(n -> !n.isEmpty()).map(n -> Integer.parseInt(n)).toList();

                result2.add(numbers.stream().filter(n -> winingNumbers.contains(n)).count());

                if (numbers.stream().filter(n -> winingNumbers.contains(n)).count() > 1) {
                    result += Math.pow(2, numbers.stream().filter(n -> winingNumbers.contains(n)).count() - 1);
                } else if (numbers.stream().filter(n -> winingNumbers.contains(n)).count() == 1) {
                    result += 1;
                }
                line = reader.readLine();
            }

        }

        List<Long> result3 = new ArrayList<>();

        for (int i = 1; i <= result2.size(); i++) {
            final int copy = i;
            if (result2.get(i - 1) >= 0) {
                long[] array = IntStream.rangeClosed(i + 1, Long.valueOf(i + result2.get(i - 1)).intValue()).mapToLong(n -> (long) n).toArray();
                long max = result3.stream().filter(n -> n.equals(Long.valueOf(copy))).count();
                result3.add(Long.valueOf(i));
                for (int l = 0; l < Math.max(1, max + 1); l++) {
                    Arrays.stream(array).forEach(n -> result3.add(n));
                }
            }
        }

        return new Pair<>(result, Long.valueOf(result3.size()));
    }


}

