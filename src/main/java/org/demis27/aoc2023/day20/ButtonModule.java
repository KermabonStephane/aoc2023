package org.demis27.aoc2023.day20;

import org.javatuples.Triplet;

import java.util.List;

public class ButtonModule extends Module {

    public ButtonModule() {
        this.name = "button";
    }

    @Override
    public List<Triplet<Module, Pulse, Module>> pulse(Module source, Pulse low, Module target, long i) {
        return null;
    }
}
