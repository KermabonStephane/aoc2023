package org.demis27.aoc2023.day19;

public interface Rule {

    public Workflow getNext(Part part);

    String getWorkflowName();

    void setWorkflow(Workflow w);
}
