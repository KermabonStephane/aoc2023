package org.demis27.aoc2023.day20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day20 {
    public long processPartOne(String s) throws IOException {
        ModuleConfiguration configuration = process(s);
        for (int i = 0; i < 1000; i++) {
            configuration.pushButton(i);
        }
        return configuration.lowOPulseNumber * configuration.highPulseNumber;
    }

    public long processPartTwo(String s) throws IOException {
        ModuleConfiguration configuration = process(s);
        Pulse result = Pulse.HIGH;
        long i = 0;
        while (result == Pulse.HIGH) {
            result = configuration.pushButton(i);
            i++;
        }
        return i;
    }

    private ModuleConfiguration process(final String filename) throws IOException {
        ModuleConfiguration configuration = new ModuleConfiguration();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                Module module = Module.readModule(line);
                if (module instanceof BroadcastModule) {
                    configuration.broadcaster = (BroadcastModule) module;
                    configuration.modules.put(module.name, module);
                }
                else {
                    configuration.modules.put(module.name, module);
                }
                line = reader.readLine();
            }
        }
        configuration.reconcil();
        return configuration;
    }

}
