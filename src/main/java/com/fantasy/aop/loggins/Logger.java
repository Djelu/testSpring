package com.fantasy.aop.loggins;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by Djelu on 02.09.2017.
 */
@Component
public class Logger {

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