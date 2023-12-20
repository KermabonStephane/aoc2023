package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    public String name;

    public List<Module> targets = new ArrayList<>();

    public List<String> moduleNames = new ArrayList<>();

    public abstract List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse low, Module target, long i);

    public void addTarget(Module target) {
        targets.add(target);
        if (target instanceof ConjonctionModule) {
            ((ConjonctionModule) target).lastPulses.put(this, Pulse.LOW);
        }

    }

    public static Module readModule(String line) {
        if (line.startsWith("broadcaster")) {
            return new BroadcastModule(line);
        }
        else if (line.startsWith("%")) {
            return new FlipFlopModule(line);
        }
        else {
            return new ConjonctionModule(line);
        }
    }

    public String toString() {
        return name;
    }
}
