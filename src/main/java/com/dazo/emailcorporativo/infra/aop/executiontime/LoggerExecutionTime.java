package com.dazo.emailcorporativo.infra.aop.executiontime;

public interface LoggerExecutionTime {

    void start();

    void end();

    void log(String concatenatedMessage);
}
