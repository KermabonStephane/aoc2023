package org.demis27.aoc2023.days.day05;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

@Slf4j
public class Day05 {
    public long processPartOne(String s) throws IOException {
        Almanac almanac = readFile(s, 0);
        List<Seed> seeds = almanac.getSeeds();
        List<Long> list = seeds.stream().map(almanac::getFinalDestination).toList();

        List<Long> sortedlist = new ArrayList<>(list);

        Collections.sort(sortedlist);

        return sortedlist.get(0);
    }

    public long processPartTwo(String s) throws IOException {
        Almanac almanac = readFile(s, 1);
        List<SeedRange> seeds = almanac.getSeedRanges();
        return seeds.stream().map(seed -> LongStream.range(seed.getInitialValue(), seed.getInitialValue() + seed.getRange())
                .map(almanac::getFinalDestination).min().orElse(Long.MAX_VALUE))
                .min(Long::compareTo).orElse(Long.MAX_VALUE);
    }

    private Almanac readFile(final String filename, int mode) throws IOException {
        Almanac almanac = new Almanac();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            int state = -1;
            while (line != null) {
                if (line.startsWith("seeds:")) {
                    if (mode == 0) {
                        almanac.setSeeds(Seed.readSeeds(line));
                    } else {
                        almanac.setSeedRanges(SeedRange.read(line));
                    }
                } else if (line.startsWith("seed-to-soil map:")) {
                    state = 0;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("seed-to-soil"));
                } else if (line.startsWith("soil-to-fertilizer map:")) {
                    state = 1;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("soil-to-fertilizer"));
                } else if (line.startsWith("fertilizer-to-water map:")) {
                    state = 2;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("fertilizer-to-water"));
                } else if (line.startsWith("water-to-light map:")) {
                    state = 3;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("water-to-light"));
                } else if (line.startsWith("light-to-temperature map:")) {
                    state = 4;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("light-to-temperature"));
                } else if (line.startsWith("temperature-to-humidity map:")) {
                    state = 5;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("temperature-to-humidity"));
                } else if (line.startsWith("humidity-to-location map:")) {
                    state = 6;
                    almanac.getSourceToDestinationLists().add(new SourceToDestinationList("humidity-to-location"));
                } else if (line.trim().isEmpty()) {
                    state = -1;
                } else if (state > -1) {
                    almanac.getSourceToDestinationLists().get(state).getSourceToDestinations().add(SourceToDestination.read(line));
                }
                line = reader.readLine();
            }
        }
        return almanac;
    }
}
