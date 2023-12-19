package org.demis27.aoc2023.days.day19;

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
        Pair<List<Workflow>, List<Part>> process = process(s);
        process.getValue0().add(new Workflow("A"));
        process.getValue0().add(new Workflow("R"));
        Map<String, Workflow> map = new HashMap<>(process.getValue0().size());
        connectWorkflows(process.getValue0(), map);

        PartSet initial = new PartSet(map.get("in"), new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000));

        execute(initial);

        return execute(initial).stream()
                .filter(p -> p.workflow.name.equals("A"))
                .map(p -> (p.x.end - p.x.start + 1) * (p.m.end - p.m.start + 1) * (p.a.end - p.a.start + 1) * (p.s.end - p.s.start + 1))
                .reduce(0L, (a, b) -> a + b);

    }

    public List<PartSet> execute(PartSet partSet) {
        List<PartSet> result = new ArrayList<>();
        if (partSet.workflow.name == "A" || partSet.workflow.name == "R") {
            result.add(partSet);
        } else {
            List<PartSet> execute = partSet.workflow.execute(partSet);
            result.addAll(execute.stream().flatMap(p -> execute(p).stream()).toList());
        }
        return result;
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
                } else if (!line.isEmpty()) {
                    parts.add(new Part(line));
                }
                line = reader.readLine();
            }
        }
        return result;
    }

}
