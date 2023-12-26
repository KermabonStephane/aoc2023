package org.demis27.aoc2023.day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;


public class ModuleConfiguration {

    public Map<String, Module> modules = new HashMap<>();

    public BroadcastModule broadcaster;

    public long lowOPulseNumber = 0L;
    public long highPulseNumber = 0L;

    public Queue<Triplet<Module, Pulse, Module>> queue = new LinkedList<>();

    public Pulse pushButton(long i) {
        queue.add(new Triplet<>(new ButtonModule(), Pulse.LOW, broadcaster));
        Triplet<Module, Pulse, Module> current = queue.peek();
        long count = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            count++;
//            System.out.println(current.getValue0() + " -" + current.getValue1() + "-> " + current.getValue2().name);
            if (current.getValue1() == Pulse.LOW) {
                lowOPulseNumber++;
            } else {
                highPulseNumber++;
            }
            current.getValue2().pulse(current.getValue0(), current.getValue1(), current.getValue2(), i).stream().forEach(next -> {
                queue.add(next);
            });
        }
//        System.out.println(count);
        return current.getValue1();
    }

    public void reconcil() {
        modules.values().stream().forEach(module -> {
            module.moduleNames.forEach(name -> {
                Module m = modules.get(name);
                if (m != null) {
                    module.addTarget(m);
                } else {
                    module.addTarget(new FinalModule(name));
                }
            });
        });

    }

}
