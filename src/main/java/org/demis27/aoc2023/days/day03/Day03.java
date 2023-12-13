package org.demis27.aoc2023.days.day03;

import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Day03 {
    public long processPartOne(String s) throws IOException {
        Pair<List<EngineNumber>, List<EngineSymbol>> process = process(s);
        long sum = process.getValue0().stream().filter(n -> isValid(n, process.getValue1())).map(n -> n.value).collect(Collectors.summarizingLong(i -> i)).getSum();
        log.info("Day 02, part 1 : {}", sum);
        return sum;
    }

    public long processPartTwo(String s) throws IOException {
        Pair<List<EngineNumber>, List<EngineSymbol>> process = process(s);
        process.getValue0().stream().filter(n -> isValid(n, process.getValue1())).map(n -> n.value).collect(Collectors.summarizingLong(i -> i)).getSum();
        long sum = process.getValue1().stream().map(EngineSymbol::gear).collect(Collectors.summarizingLong(i -> i)).getSum();
        log.info("Day 02, part 2 : {}", sum);
        return sum;
    }

    private Pair<List<EngineNumber>, List<EngineSymbol>> process(final String filename) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        }
        List<EngineNumber> numbers = extractNumbers(lines);
        List<EngineSymbol> symbols = extractSymbols(lines);

        return new Pair<>(numbers, symbols);
    }


    private List<EngineSymbol> extractSymbols(List<String> lines) {
        List<EngineSymbol> symbols = new ArrayList<>();
        for (int l = 0; l < lines.size(); l++) {
            for (int i = 0; i < lines.get(l).length(); i++) {
                char c = lines.get(l).charAt(i);
                if (c != '.') {
                    if (!isDigit(c)) {
                        symbols.add(new EngineSymbol(l, i, c));
                    }
                }
            }
        }
        return symbols;
    }

    private List<EngineNumber> extractNumbers(List<String> lines) {
        List<EngineNumber> numbers = new ArrayList<>();
        for (int l = 0; l < lines.size(); l++) {
            String onreading = "";
            for (int i = 0; i < lines.get(l).length(); i++) {
                char c = lines.get(l).charAt(i);
                if (isDigit(c)) {
                    onreading = onreading + c;
                } else {
                    if (onreading.length() > 0) {
                        numbers.add(new EngineNumber(Integer.parseInt(onreading), l, i - onreading.length()));
                        onreading = "";
                    }
                }
            }
            if (onreading.length() > 0) {
                numbers.add(new EngineNumber(Integer.parseInt(onreading), l, lines.get(l).length() - onreading.length()));
            }
        }
        return numbers;
    }

    private static boolean isValid(EngineNumber n, List<EngineSymbol> symbols) {
        return symbols.stream().anyMatch(s -> isNear(n, s));
    }

    private static boolean isNear(EngineNumber n, EngineSymbol s) {
        boolean result = false;
        if (s.lineNumber < n.lineNumber - 1) {
            result = false;
        }
        if (s.lineNumber > n.lineNumber + 1) {
            result = false;
        }
        if (s.lineNumber == n.lineNumber) {
            result = s.position == n.startPosition - 1 || s.position == n.endPosition + 1;
        }
        if (s.lineNumber == n.lineNumber - 1 || s.lineNumber == n.lineNumber + 1) {
            result = s.position >= n.startPosition - 1 && s.position <= n.endPosition + 1;
        }
        if (result) {
            s.addEngineNumber(n);
        }
        return result;
    }

    public static boolean isDigit(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

}
