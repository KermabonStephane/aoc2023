package org.demis27.aoc2023.days.day19;

import java.util.List;

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
        return List.of(new PartSet(workflow, initial.x, initial.m, initial.a, initial.s));
    }

}
