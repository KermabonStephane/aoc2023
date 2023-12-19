package org.demis27.aoc2023.day19;

public class FunctionRule implements Rule{

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
        }
        else {
            return (part.getData(type).value < value) ? workflow : null;
        }
    }
}
