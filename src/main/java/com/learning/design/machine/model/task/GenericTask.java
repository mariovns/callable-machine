package com.learning.design.machine.model.task;

import java.util.function.Function;

public class GenericTask extends Task {
    private Function<TaskInputContext, TaskOutputContext> function;

    public GenericTask(TaskDef activityDef, Function<TaskInputContext, TaskOutputContext> function) {
        super(activityDef);
        this.function = function;
    }

    @Override
    protected TaskOutputContext perform(TaskInputContext input) {
        System.out.println("Hello World");
        return function.apply(input);
    }
}
