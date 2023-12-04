package org.demis27.aoc2023.day01;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Day01 {

    public long getResultPartOne(final String filename) throws IOException {
        AtomicLong result = new AtomicLong();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {

            String line = reader.readLine();
            result.set(0L);

            while (line != null) {
                String numbers = line.replaceAll("[a-zA-A]", "");
                if (numbers.length() > 1) {
                    result.addAndGet(Long.parseLong(numbers.substring(0, 1)) * 10L + Long.parseLong(numbers.substring(numbers.length() - 1)));
                } else if (numbers.length() == 1) {
                    result.addAndGet(Long.parseLong(numbers.substring(0, 1)) * 11L);
                }
                line = reader.readLine();
            }
        }
        log.info("result {}", result.get());
        return result.get();
    }

    public long getResultPartTwo(final String filename) throws IOException {
        AtomicLong result = new AtomicLong();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {

            String line = reader.readLine();
            result.set(0L);

            while (line != null) {
                String numbers = line.replaceAll("one", "one1one")
                        .replaceAll("two", "two2two")
                        .replaceAll("three", "three3three")
                        .replaceAll("four", "four4four")
                        .replaceAll("five", "five5five")
                        .replaceAll("six", "six6six")
                        .replaceAll("seven", "seven7seven")
                        .replaceAll("eight", "eight8eight")
                        .replaceAll("nine", "nine9nine")
                        .replaceAll("[a-zA-A]", "");
                if (numbers.length() > 1) {
                    result.addAndGet(Long.parseLong(numbers.substring(0, 1)) * 10L + Long.parseLong(numbers.substring(numbers.length() - 1)));
                } else if (numbers.length() == 1) {
                    result.addAndGet(Long.parseLong(numbers.substring(0, 1)) * 11L);
                }
                line = reader.readLine();
            }
        }
        log.info("result {}", result.get());
        return result.get();
    }

}
