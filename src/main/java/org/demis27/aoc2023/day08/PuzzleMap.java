package org.demis27.aoc2023.day08;

import lombok.Getter;
import lombok.Setter;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class PuzzleMap {

    private String instruction;

    private Map<String, Pair<String, String>> choices = new HashMap<>();

    public void addChoice(String line) {
        choices.put(line.substring(0, 3), new Pair<>(line.substring(7, 10), line.substring(12, 15)));
    }

    public List<String> getAllStartPositions() {
        return choices.keySet().stream().filter(k -> k.endsWith("A")).sorted().toList();
    }

    public List<String> getAllEndPositions() {
        return choices.keySet().stream().filter(k -> k.endsWith("Z")).sorted().toList();
    }

}
