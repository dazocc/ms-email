package com.dazo.emailcorporativo.infra.aop.executiontime.usecase;

import com.dazo.emailcorporativo.infra.aop.executiontime.LoggerExecutionTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("LOGGER_GERAL")
@AllArgsConstructor
@Slf4j
public class LoggerGeral {

    private final LoggerExecutionTime loggerExecutionTime;

    @Around(value = "execution(* com.dazo.emailcorporativo.email..*(..))")
    public Object controllerExecutionTimeLog(ProceedingJoinPoint proceedingJoinPoint) {

        loggerExecutionTime.start();

        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("Erro ao executar controller execution", e);
        }

        loggerExecutionTime.end();

        String classPlusMethod = montaClassePlusMetodo(proceedingJoinPoint);

        loggerExecutionTime.log(classPlusMethod + " - Tempo de execução: ");

        return object;
    }

    private String montaClassePlusMetodo(ProceedingJoinPoint proceedingJoinPoint) {

        String fullNameClass = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String simpleName = fullNameClass.substring(fullNameClass.lastIndexOf(".") + 1);

        return simpleName + "."+ proceedingJoinPoint.getSignature().getName();
    }

}