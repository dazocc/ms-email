package com.dazo.emailcorporativo.infra.aop.executiontime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrinterLoggerExecutionTime implements LoggerExecutionTime {

    private long startTime;
    private long endTime;

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void end() {
        endTime = System.currentTimeMillis();
    }

    @Override
    public void log(String concatenatedMessage) {
        log.info(concatenatedMessage + ((((float)endTime - startTime)) / 1000) + " segundos");
    }
}
