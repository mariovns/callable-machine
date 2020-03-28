package com.learning.design.machine.model.task;

import com.learning.design.machine.model.activity.Activity;
import com.learning.design.machine.model.activity.ActivityDef;

public abstract class Task extends Activity<TaskInputContext, TaskOutputContext, TaskDef> {

    public Task(TaskDef activityDef) {
        super(activityDef);
    }

    @Override
    protected abstract TaskOutputContext perform(TaskInputContext input) ;


}
