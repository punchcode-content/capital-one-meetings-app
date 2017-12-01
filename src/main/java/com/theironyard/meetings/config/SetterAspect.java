package com.theironyard.meetings.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SetterAspect {
    @Pointcut("within(com.theironyard..*)")
    public void inOurCode() {
    }

    @Pointcut("execution(* *(..))")
    public void onExec() {
    }

    @Pointcut("execution(* get*(..))")
    public void onGet() {
    }

    @Before(value = "inOurCode() && onExec()")
    public void ourMethodCalled(JoinPoint joinPoint) {
        System.out.println("method called");
        System.out.println(joinPoint.getSignature().toLongString());
    }

    @AfterReturning(pointcut = "inOurCode() && onGet()", returning = "retval")
    public void showReturnValue(JoinPoint joinPoint, Object retval) {
        System.out.println("Getter called");
        System.out.println(joinPoint.getSignature().toString());
        System.out.println("Returning " + retval.toString());
    }
}
