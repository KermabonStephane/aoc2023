package org.demis27.aoc2023.day19;

public class DirectRule implements Rule{

    public Workflow workflow;

    public String workflowName;

    public DirectRule(String s) {
        workflowName = s;
    }

    @Override
    public Workflow getNext(Part part) {
        return workflow;
    }
}
