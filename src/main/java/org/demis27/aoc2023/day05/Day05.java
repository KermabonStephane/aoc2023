package org.demis27.aoc2023.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day05 {
    public long processPartOne(String s) throws IOException {
        process(s);
        return 0;
    }

    public long processPartTwo(String s) throws IOException {
        process(s);
        return 0;
    }

    private void process(final String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {

                line = reader.readLine();
            }
        }
    }
}
