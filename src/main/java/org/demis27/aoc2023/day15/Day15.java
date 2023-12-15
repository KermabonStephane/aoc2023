package org.demis27.aoc2023.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day15 {
    public long processPartOne(String s) throws IOException {
        process(s);
        return 0L;
    }

    public long processPartTwo(String s) throws IOException {
        process(s);
        return 0L;
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
