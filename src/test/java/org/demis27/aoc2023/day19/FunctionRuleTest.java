package org.demis27.aoc2023.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class FunctionRuleTest {

    @Test
    void less() {
        FunctionRule rule = new FunctionRule("a<2006:qkq");
        Workflow workflow = new Workflow();
        workflow.rules.add(rule);
        rule.workflow = workflow;

        PartSet partSet = new PartSet(null, new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000));
        List<PartSet> execute = rule.execute(partSet);

        Assertions.assertNotNull(execute);
        Assertions.assertEquals(2, execute.size());
        PartSet one = execute.get(0);
        PartSet two = execute.get(1);

        Assertions.assertEquals(new PartRange(1, 4000), one.x);
        Assertions.assertEquals(new PartRange(1, 4000), one.m);
        Assertions.assertEquals(new PartRange(1, 2005), one.a);
        Assertions.assertEquals(new PartRange(1, 4000), one.s);
        Assertions.assertEquals(workflow, one.workflow);

        Assertions.assertEquals(new PartRange(1, 4000), two.x);
        Assertions.assertEquals(new PartRange(1, 4000), two.m);
        Assertions.assertEquals(new PartRange(2006, 4000), two.a);
        Assertions.assertEquals(new PartRange(1, 4000), two.s);
        Assertions.assertNull(two.workflow);
    }

    @Test
    void greater() {
        FunctionRule rule = new FunctionRule("a>2006:qkq");
        Workflow workflow = new Workflow();
        workflow.rules.add(rule);
        rule.workflow = workflow;

        PartSet partSet = new PartSet(null, new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000), new PartRange(1, 4000));
        List<PartSet> execute = rule.execute(partSet);

        Assertions.assertNotNull(execute);
        Assertions.assertEquals(2, execute.size());
        PartSet one = execute.get(0);
        PartSet two = execute.get(1);

        Assertions.assertEquals(new PartRange(1, 4000), one.x);
        Assertions.assertEquals(new PartRange(1, 4000), one.m);
        Assertions.assertEquals(new PartRange(2007, 4000), one.a);
        Assertions.assertEquals(new PartRange(1, 4000), one.s);
        Assertions.assertEquals(workflow, one.workflow);

        Assertions.assertEquals(new PartRange(1, 4000), two.x);
        Assertions.assertEquals(new PartRange(1, 4000), two.m);
        Assertions.assertEquals(new PartRange(1, 2006), two.a);
        Assertions.assertEquals(new PartRange(1, 4000), two.s);
        Assertions.assertNull(two.workflow);
    }
}