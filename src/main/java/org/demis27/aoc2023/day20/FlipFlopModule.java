package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlipFlopModule extends Module {

    public boolean on = false;

    // %b -> con
    public FlipFlopModule(String line) {
        name = line.substring(1, line.indexOf("-")).trim();
        moduleNames = Arrays.stream(line.substring(line.indexOf('-') + 2).split(",")).map(s -> s.trim()).toList();
    }

    @Override
    public List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse pulse, Module target, long i) {
        if (pulse == Pulse.LOW) {
            on = !on;
            List<Triplet<Module, Pulse, Module>> result = new ArrayList<>();
            for (int t = 0; t < targets.size(); t++) {
                result.add(new Triplet<>(target, on ? Pulse.HIGH : Pulse.LOW, targets.get(t)));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

}
