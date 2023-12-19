package org.demis27.aoc2023.day19;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day19 {
    public long processPartOne(String s) throws IOException {
        Pair<List<Workflow>, List<Part>> process = process(s);
        return 0L;
    }

    public long processPartTwo(String s) throws IOException {
        process(s);
        return 0L;
    }

    private Pair<List<Workflow>, List<Part>> process(final String filename) throws IOException {
        List<Workflow> workflows = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        Pair<List<Workflow>, List<Part>> result = new Pair<>(workflows, parts);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            boolean readWorkflow = true;
            while (line != null) {

                if (line.isEmpty()) {
                    readWorkflow = false;
                }
                if (readWorkflow && !line.isEmpty()) {
                    workflows.add(new Workflow(line));
                }
                else if (!line.isEmpty()){
                    parts.add(new Part(line));
                }
                line = reader.readLine();
            }
        }
        return result;
    }

}
