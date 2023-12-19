package org.demis27.aoc2023.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Workflow {

    public String name;
    public List<Rule> rules = new ArrayList<>();

    // px{a<2006:qkq,m>2090:A,rfg}
    public Workflow(String line) {
        if (line.length() == 1) {
            name = line;
        }
        else {
            String[] split = line.substring(0, line.length() - 1).split("\\{");
            name = split[0];
            String[] splitRule = split[1].split(",");
            for (int i = 0; i < splitRule.length; i++) {
                if (splitRule[i].contains(":")) {
                    rules.add(new FunctionRule(splitRule[i]));
                } else {
                    rules.add(new DirectRule(splitRule[i]));
                }

            }
        }
    }

    public Workflow getNext(Part part) {
        return rules.stream().map(rule -> rule.getNext(part)).filter(Objects::nonNull).findFirst().get();
    }

}
