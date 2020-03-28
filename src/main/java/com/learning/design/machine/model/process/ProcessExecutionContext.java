package com.learning.design.machine.model.process;

import com.learning.design.machine.model.DataContext;

import java.util.Map;

/**
 * If some duplicate values are going to put-in, it can be handled by
 * over-riding {@link java.util.Map } {@code putAll} method
 * */
public class ProcessExecutionContext<S, O> extends DataContext {
    private ProcessInputContext processInputContext = new ProcessInputContext();
    private ProcessOutputContext processOutputContext = new ProcessOutputContext();

    public ProcessInputContext getInputContext(){
        /* moved to putAll, so that adding only the required element into process-input-context,
         as this method is called multiple time on ProcessExecutionContext
         */
//        processInputContext.putAll(this);
        return processInputContext;
    }

    public ProcessOutputContext getOutputContext(){
        processOutputContext.putAll(this);
        return processOutputContext;
    }

    @Override
    public void putAll(Map m) {
        super.putAll(m);
        processInputContext.putAll(m);
    }
}
