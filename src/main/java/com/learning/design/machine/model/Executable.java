package com.learning.design.machine.model;

/**
 * The object from this class supports execution on the input data
 * and returns back the processed output data*/
public interface Executable {

    OutputContext execute(InputContext input);
}
