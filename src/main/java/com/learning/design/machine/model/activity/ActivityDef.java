package com.learning.design.machine.model.activity;

import com.learning.design.machine.model.Handler;

import java.util.LinkedList;
import java.util.List;

// Immutable class
public abstract class ActivityDef {
    protected final List<String> outputDataList;
    protected String serviceId;
    private final boolean asynchronized;
    protected final Handler handler;

    protected ActivityDef(ActivityDefBuilder activityDefBuilder){
        this.outputDataList = activityDefBuilder.outputDataList;
        this.serviceId = activityDefBuilder.operationId;
        this.asynchronized = activityDefBuilder.asynchronized;
        this.handler = activityDefBuilder.handler;
    }

    public List<String> getOutputDataList() {
        return (LinkedList<String>)((LinkedList)outputDataList).clone();
    }

    public String getServiceId() {
        return serviceId;
    }

    public boolean isAsynchronized() {
        return asynchronized;
    }

    public Handler getHandler() {
        return handler;
    }

    public static abstract class ActivityDefBuilder <T extends ActivityDef, S extends ActivityDefBuilder>{
        private List<String> outputDataList = new LinkedList<>();;
        private String operationId;
        private boolean asynchronized = false;
        private Handler handler;

        public S setOutputDataList(List<String> outputDataList){
            if( outputDataList != null) {
                this.outputDataList = outputDataList;
            }
            return (S) this;
        }

        public S addOutputData(String outputData){
            this.outputDataList.add(outputData);
            return (S) this;
        }

        public S addOutputDataList(List<String> outputDataList){
            if( outputDataList != null) {
                this.outputDataList.addAll(outputDataList);
            }
            return (S) this;
        }

        public S setOperationId(String operationId){
            this.operationId = operationId;
            return (S) this;
        }

        public S setAsynchronized(boolean asynchronized){
            this.asynchronized = asynchronized;
            return (S) this;
        }

        public S setHandler(Handler handler){
            this.handler = handler;
            return (S) this;
        }

        public abstract T build();
    }
}
