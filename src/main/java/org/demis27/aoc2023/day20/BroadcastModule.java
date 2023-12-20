package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.Arrays;
import java.util.List;

public class BroadcastModule extends Module {

    public BroadcastModule(String line) {
        name = "broadcast";
        String[] split = line.substring(line.indexOf('-') + 2).split(",");
        moduleNames = Arrays.stream(split).map(s -> s.trim()).toList();
    }

    @Override
    public List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse pulse, Module target, long i) {
        return targets.stream().map(module -> new Triplet<>(target, pulse, module)).toList();
    }
}
