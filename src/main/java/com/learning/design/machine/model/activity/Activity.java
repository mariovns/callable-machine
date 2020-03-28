package com.learning.design.machine.model.activity;

import com.learning.design.machine.model.Executable;
import com.learning.design.machine.model.InputContext;
import com.learning.design.machine.model.OutputContext;


// TODO : ServiceLocal variables with higher preference??
// TODO : should ProcessContext inherit fruom TaskContext : required to process taskInputContext with ProcessInputContext

/**
 * The abstract implementation of {@link Executable}.
 * The execution is guided by an implementation of {@link ActivityDef}.
 * The execution ensures that data can be handled pre & post execution.
 * It also expose only configured data to the caller
 */
public abstract class Activity<C extends InputContext, D extends OutputContext, E extends ActivityDef> implements Executable/*, Callable */ {
    protected E activityDef;

    public Activity(E activityDef) {
        this.activityDef = activityDef;
    }

    public OutputContext execute(InputContext inputContext) {
        activityDef.getHandler().preHandle(inputContext);
        D outputContext = perform((C) inputContext);
        activityDef.getHandler().postHandle(outputContext);
        exposeConfiguredData(outputContext);
        return outputContext;
    }

    private void exposeConfiguredData(D outputContext) {
        outputContext.keySet().forEach(key -> {
            if (!activityDef.outputDataList.contains(key)) {
                outputContext.remove(key);
            }
        });
    }

    protected abstract D perform(C input);
}
