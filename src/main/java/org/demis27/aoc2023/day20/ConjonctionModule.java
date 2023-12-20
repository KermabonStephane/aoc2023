package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConjonctionModule extends Module {

    public Map<Module, Pulse> lastPulses = new HashMap<>();

    // &con -> output
    public ConjonctionModule(String line) {
        name = line.substring(1, line.indexOf("-")).trim();
        moduleNames = Arrays.stream(line.substring(line.indexOf('-') + 2).split(",")).map(s -> s.trim()).toList();
    }

    @Override
    public List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse pulse, Module target, long i) {
        lastPulses.put(source, pulse);
        boolean allHigh = lastPulses.values().stream().allMatch(p -> p == Pulse.HIGH);
        return allHigh ? targets.stream().map(module -> new Triplet<>(target, Pulse.LOW, module)).toList()
                : targets.stream().map(module -> new Triplet<>(target, Pulse.HIGH, module)).toList();
    }
}
