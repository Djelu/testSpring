package com.fantasy.aop.loggins;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by Djelu on 02.09.2017.
 */
@Component
@Aspect
public class Logger {

    @Pointcut("execution(* com.fantasy.aop.objects.interfaces.Manager.*(..))")
    private void allMethods(){
    }

    public void printValue(Object obj){
        System.out.println(obj);
    }

    public void init(){
        System.out.println("init");
    }

    public void close(){
        System.out.println("close");
    }

    public void ex(Exception ex){
        System.out.println("ex: " + ex);
    }

    @Around("allMethods() && @annotation(com.fantasy.aop.annotations.ShowTime)")
    public Object watchTime(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        System.out.println("method begin: " + joinPoint.getSignature().toShortString());
        Object output = null;

        for(Object object: joinPoint.getArgs()){
            System.out.println("Param: " + object);
        }

        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinPoint.getSignature().toShortString() + ", time=" + time + " ms");

        return output;
    }

    @AfterReturning(pointcut = "allMethods() && @annotation(com.fantasy.aop.annotations.ShowResult)", returning = "obj")
    public void print(Object obj){
        System.out.println("Print info begin:");

        if(obj instanceof Set){
            Set set = (Set) obj;
            for(Object object: set){
                System.out.println(object);
            }
        } else  if(obj instanceof Map){
            Map map = (Map) obj;
            for(Object object: map.keySet()){
                System.out.println("key= " + object + ", " + map.get(object));
            }
        }

        System.out.println("Print info end.");
        System.out.println();
    }
}
