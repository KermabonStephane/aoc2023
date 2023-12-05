package org.demis27.aoc2023.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceToDestinationList {

    private String name;
    private List<SourceToDestination> sourceToDestinations = new ArrayList<>();

    public SourceToDestinationList(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SourceToDestinationList{" +
                "name='" + name + '\'' +
                ", sourceToDestinations=" + sourceToDestinations +
                '}';
    }

    public long getDestination(long tmp) {
        Optional<SourceToDestination> first = sourceToDestinations.stream().filter(std -> std.concerned(tmp)).findFirst();
        return first.map(sourceToDestination -> sourceToDestination.getDestination(tmp)).orElse(tmp);
    }
}
