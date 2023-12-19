package org.demis27.aoc2023.day19;

import java.util.List;

public interface Rule {

    public Workflow getNext(Part part);

    String getWorkflowName();

    void setWorkflow(Workflow w);

    List<PartSet> execute(PartSet initial);
}
