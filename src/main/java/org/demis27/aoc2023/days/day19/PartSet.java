package org.demis27.aoc2023.days.day19;

public class PartSet {

    public Workflow workflow;

    public PartRange x;

    public PartRange m;

    public PartRange a;

    public PartRange s;

    public PartSet(Workflow workflow, PartRange x, PartRange m, PartRange a, PartRange s) {
        this.workflow = workflow;
        this.x = x;
        this.m = m;
        this.a = a;
        this.s = s;
    }
}
