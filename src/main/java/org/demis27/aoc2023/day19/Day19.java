package org.demis27.aoc2023.day19;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day19 {
    public long processPartOne(String s) throws IOException {
        Pair<List<Workflow>, List<Part>> process = process(s);
        process.getValue0().add(new Workflow("A"));
        process.getValue0().add(new Workflow("R"));
        Map<String, Workflow> map = new HashMap<>(process.getValue0().size());
        connectWorkflows(process.getValue0(), map);
        return process.getValue1().stream().filter(part -> applyWorkflows(map, part)).map(part -> part.a.value + part.m.value + part.x.value + part.s.value).collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    private boolean applyWorkflows(Map<String, Workflow> map, Part part) {
        Workflow currentWorkflow = map.get("in");
        while (!"A".equals(currentWorkflow.name) && !"R".equals(currentWorkflow.name)) {
            currentWorkflow = currentWorkflow.getNext(part);
        }
        return "A".equals(currentWorkflow.name);
    }

    private void connectWorkflows(List<Workflow> workflows, Map<String, Workflow> map) {
        workflows.stream().forEach(w -> map.put(w.name, w));
        workflows.stream().flatMap(w -> w.rules.stream()).forEach(r -> r.setWorkflow(map.get(r.getWorkflowName())));
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
