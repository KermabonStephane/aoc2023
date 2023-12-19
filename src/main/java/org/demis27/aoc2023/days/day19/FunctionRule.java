package org.demis27.aoc2023.days.day19;

import java.util.List;

public class FunctionRule implements Rule {

    public DataType type;

    public char operator;

    public int value;

    public Workflow workflow;

    public String workflowName;

    // a<2006:qkq
    public FunctionRule(String s) {
        String[] split = s.split(":");
        workflowName = split[1];
        operator = split[0].charAt(1);
        type = switch (split[0].charAt(0)) {
            case 'a' -> DataType.A;
            case 'x' -> DataType.X;
            case 'm' -> DataType.M;
            case 's' -> DataType.S;
            default -> throw new IllegalStateException("Unexpected value: " + split[0].charAt(0));
        };
        value = Integer.parseInt(split[0].substring(2));
    }

    @Override
    public Workflow getNext(Part part) {
        if (operator == '>') {
            return (part.getData(type).value > value) ? workflow : null;
        } else {
            return (part.getData(type).value < value) ? workflow : null;
        }
    }

    @Override
    public String getWorkflowName() {
        return workflowName;
    }

    @Override
    public void setWorkflow(Workflow w) {
        this.workflow = w;
    }

    @Override
    public List<PartSet> execute(PartSet initial) {
        if (operator == '>') {
            return switch (type) {
                case X -> List.of(
                        new PartSet(workflow, new PartRange(value + 1, initial.x.end) , initial.m, initial.a, initial.s),
                        new PartSet(null, new PartRange(initial.x.start, value) , initial.m, initial.a, initial.s));
                case M -> List.of(
                        new PartSet(workflow, initial.x , new PartRange(value + 1, initial.m.end), initial.a, initial.s),
                        new PartSet(null, initial.x , new PartRange(initial.m.start, value), initial.a, initial.s));
                case A -> List.of(
                        new PartSet(workflow, initial.x , initial.m, new PartRange(value + 1, initial.a.end), initial.s),
                        new PartSet(null, initial.x , initial.m, new PartRange(initial.a.start, value), initial.s));
                case S -> List.of(
                        new PartSet(workflow, initial.x , initial.m, initial.a, new PartRange(value + 1, initial.s.end)),
                        new PartSet(null, initial.x , initial.m, initial.a, new PartRange(initial.s.start, value)));
            };
        }
        else {
            return switch (type) {
                case X -> List.of(
                        new PartSet(workflow, new PartRange(initial.x.start, value - 1) , initial.m, initial.a, initial.s),
                        new PartSet(null, new PartRange(value , initial.x.end) , initial.m, initial.a, initial.s));
                case M -> List.of(
                        new PartSet(workflow, initial.x , new PartRange(initial.m.start, value - 1), initial.a, initial.s),
                        new PartSet(null, initial.x , new PartRange(value , initial.m.end), initial.a, initial.s));
                case A -> List.of(
                        new PartSet(workflow, initial.x , initial.m, new PartRange(initial.a.start, value - 1), initial.s),
                        new PartSet(null, initial.x , initial.m, new PartRange(value , initial.a.end), initial.s));
                case S -> List.of(
                        new PartSet(workflow, initial.x , initial.m, initial.a, new PartRange(initial.s.start, value +-1)),
                        new PartSet(null, initial.x , initial.m, initial.a, new PartRange(value , initial.s.end)));
            };
        }
    }
}
