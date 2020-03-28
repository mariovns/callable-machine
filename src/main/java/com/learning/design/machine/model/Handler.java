package com.learning.design.machine.model;

public interface Handler<C extends DataContext> {
    C preHandle(C data);

    C postHandle(C data);
}
