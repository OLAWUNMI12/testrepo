package com.Aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @After("execution(public String welcome(..))")
    public void firstAspect() {
        System.out.println("speaking from almighty aspect");
    }

    @Pointcut("execution( * home(..))")
    public void doSomething() {
    }

    @Pointcut("execution(public String welcome(..))")
    public void goHome() {
    }

    @Before("!doSomething() && goHome() ")
    public void logSomething() {
        System.out.println("just doing my job brother");
    }
}

