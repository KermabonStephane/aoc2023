package org.demis27.aoc2023.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Almanac {

    private List<Seed> seeds;

    private List<DynamicSeed> dynamicSeeds;

    private List<SourceToDestinationList> sourceToDestinationLists = new ArrayList<>();

    @Override
    public String toString() {
        return "Almanac{" +
                "seeds=" + seeds +
                ", sourceToDestinationLists=" + sourceToDestinationLists +
                '}';
    }

    public long getFinalDestination(Seed seed) {
        //log.info("final destination for {}", seed.getValue());
        long tmp =  seed.getValue();
        for (SourceToDestinationList std : sourceToDestinationLists) {
            tmp = std.getDestination(tmp);
        }
        return tmp;
    }
}
