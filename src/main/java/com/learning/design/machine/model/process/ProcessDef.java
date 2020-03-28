package com.learning.design.machine.model.process;

import com.learning.design.machine.model.activity.Activity;
import com.learning.design.machine.model.activity.ActivityDef;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProcessDef extends ActivityDef {
    private final Map<Activity, List<Activity>> activityGraph;
    private final Activity rootActivity;
    private ProcessDef(ProcessDefBuilder builder) {
        super(builder);
        this.activityGraph = builder.activityGraph;
        this.rootActivity = builder.rootActivity;
    }

    public Map<Activity, List<Activity>> getActivityGraph() {
        return activityGraph;
    }

    public Activity getRootActivity() {
        return rootActivity;
    }

    public static class ProcessDefBuilder extends ActivityDefBuilder<ProcessDef, ProcessDefBuilder> {
        private final Map<Activity, List<Activity>> activityGraph = new LinkedHashMap<>();
        private Activity rootActivity;

        @Override
        public ProcessDef build() {
            return null;
        }

        public ProcessDefBuilder addEdge(Activity from, Activity to){
            if( activityGraph.containsKey(from)){
                activityGraph.get(from).add(to);
            } else{
                List<Activity> toList = new LinkedList<>();
                toList.add(to);
                if(activityGraph.isEmpty()){
                    rootActivity = from;
                }
                activityGraph.put(from, toList);
            }
            return this;
        }
    }
}
