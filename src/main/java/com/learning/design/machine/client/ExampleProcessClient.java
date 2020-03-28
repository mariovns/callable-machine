package com.learning.design.machine.client;

import com.learning.design.machine.model.process.Process;
import com.learning.design.machine.model.process.ProcessDef;
import com.learning.design.machine.model.process.ProcessInputContext;
import com.learning.design.machine.model.task.GenericTask;
import com.learning.design.machine.model.task.TaskDef;
import com.learning.design.machine.model.task.TaskOutputContext;

public class ExampleProcessClient implements Runnable{
    @Override
    public void run() {
        Process p = prepareProcess();
        p.execute(new ProcessInputContext());
    }

    private Process prepareProcess() {
        ProcessDef.ProcessDefBuilder builder = new ProcessDef.ProcessDefBuilder();
        ProcessDef def = builder.addEdge(getGenericTask(), null).
                addOutputData("output").
                setOperationId("abc").
                build();
        return new Process(def);
    }

    private GenericTask getGenericTask() {
        TaskDef.TaskDefBuilder builder = new TaskDef.TaskDefBuilder();
        TaskDef def = builder.addOutputData("output").setOperationId("abc").build();
        return new GenericTask(def, (taskInputContext -> new TaskOutputContext()));
    }
}
