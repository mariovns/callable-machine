package com.learning.design.machine.model.process;

import com.learning.design.machine.model.OutputContext;
import com.learning.design.machine.model.activity.Activity;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// TODO : 2 service pushing same key, value & hence overriding : can be done by  overriding putAll in ProcExecContext
public class Process extends Activity<ProcessInputContext, ProcessOutputContext, ProcessDef> {
    public Process(ProcessDef processDef) {
        super(processDef);
    }

    @Override
    protected ProcessOutputContext perform(ProcessInputContext input) {
        return process(activityDef.getRootActivity(), input);
    }

    private ProcessOutputContext process(Activity activity, ProcessInputContext inputContext) {
        //execution context life remains till the scope of its processing
        ProcessExecutionContext processExecutionContext = new ProcessExecutionContext();
        processExecutionContext.putAll(inputContext);

        //Execute this node
        if (activityDef.isAsynchronized()) {
            Future<OutputContext> outputContextFuture = Executors.newSingleThreadExecutor().submit(new Callable<OutputContext>() {
                public OutputContext call() {
                    return activity.execute(processExecutionContext.getInputContext());
                }
            });
        } else {
            processExecutionContext.putAll(activity.execute(processExecutionContext.getInputContext()));
        }

        //Execute all of its child nodes
        for (Activity nextActivity : activityDef.getActivityGraph().get(activity)) {
            processExecutionContext.putAll(process(nextActivity, processExecutionContext.getInputContext()));
        }

        return processExecutionContext.getOutputContext();
    }

}
