package org.demis27.aoc2023.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 {
    public long processPartOne(String s) throws IOException {
        List<String> hashs = process(s);
        return hashs.stream().map(hash -> aocHash(hash)).collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    public int aocHash(String hash) {
        int result = 0;
        for (int i = 0; i < hash.length(); i++) {
            int ascii = hash.charAt(i);
            result += ascii;
            result = (result * 17) % 256;
        }
        return result;
    }

    public long processPartTwo(String s) throws IOException {
        List<String> hashs = process(s);
        Box[] boxes = new Box[256];
        // create boxes
        for (int i = 0; i < hashs.size(); i++) {
            String initialization = hashs.get(i);
            int hashValue = aocHash(initialization.split("=|-")[0]);
            if (initialization.contains("=")) {
                if (boxes[hashValue] == null) {
                    boxes[hashValue] = new Box();
                }
                boxes[hashValue].addLens(new Lens(initialization));
            } else {
                if (boxes[hashValue] != null) {
                    boxes[hashValue].removeLens(new Lens(initialization));
                }
            }
        }
        long result = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] != null && !boxes[i].map.isEmpty()) {
                result += boxes[i].map.values().stream().map(l -> ((l.position + 1) * l.focal)).collect(Collectors.summarizingInt(v -> v)).getSum() * (i + 1);
            }
        }
        return result;
    }

    public void displayBoxes(Box[] boxes) {
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] != null && !boxes[i].map.isEmpty()) {
                System.out.println(boxes[i].toString(i));
            }
        }
    }

    private List<String> process(final String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            return Arrays.stream(line.split(",")).toList();
        }
    }

}
