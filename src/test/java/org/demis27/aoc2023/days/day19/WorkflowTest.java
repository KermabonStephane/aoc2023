package org.demis27.aoc2023.days.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WorkflowTest {

    @Test
    void init() {
        Workflow workflow = new Workflow("in{s<1351:px,qqz}");
        ((FunctionRule)workflow.rules.get(0)).workflow = new Workflow("px{a<2006:qkq,m>2090:A,rfg}");
        ((DirectRule)workflow.rules.get(1)).workflow = new Workflow("qqz{s>2770:qs,m<1801:hdj,R}");
        PartSet partSet = new PartSet(workflow, new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000));

        List<PartSet> execute = workflow.execute(partSet);

        Assertions.assertNotNull(execute);
        Assertions.assertEquals(2, execute.size());
        Assertions.assertEquals("px", execute.get(0).workflow.name);
        Assertions.assertEquals("qqz", execute.get(1).workflow.name);

    }

}