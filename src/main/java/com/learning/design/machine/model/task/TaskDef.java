package com.learning.design.machine.model.task;

import com.learning.design.machine.model.activity.ActivityDef;

public class TaskDef extends ActivityDef {
    protected TaskDef(ActivityDefBuilder activityDefBuilder) {
        super(activityDefBuilder);
    }

    public static class TaskDefBuilder extends ActivityDefBuilder<TaskDef, TaskDefBuilder>{
        @Override
        public TaskDef build() {
            return new TaskDef(this);
        }
    }
}
