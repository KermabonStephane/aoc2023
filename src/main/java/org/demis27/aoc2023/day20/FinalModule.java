package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.Collections;
import java.util.List;

public class FinalModule extends Module{

    public FinalModule(String name) {
        this.name = name;
    }

    @Override
    public List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse pulse, Module target, long i) {
//        if (pulse == Pulse.LOW) {
//            System.out.println("final " + pulse + " " + i);
//        }
        return Collections.emptyList();
    }
}
