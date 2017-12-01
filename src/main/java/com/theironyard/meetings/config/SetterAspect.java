package com.theironyard.meetings.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SetterAspect {
    @Pointcut("execution(com.theironyard.* set*(*))")
    public void onSetterExec() {
    }

    @Before(value = "execution(com.theironyard.meetings.entities.* set*(*))")
    public void logSetter(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Joinpoint " + joinPoint.getSignature());
    }
}
