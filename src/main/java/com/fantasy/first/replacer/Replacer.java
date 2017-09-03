package com.fantasy.first.replacer;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by Djelu on 31.08.2017.
 */
public class Replacer implements MethodReplacer {

    public Object reimplement(Object target, Method method, Object[] args)
            throws Throwable {

        if (isFormatMessageMethod(method)) {

            System.out.println("Do nothing...");
            return null;
        } else {
            throw new IllegalArgumentException("Unable to reimplement method "
                    + method.getName());
        }
    }

    private boolean isFormatMessageMethod(Method method) {

        // check correct number of params
        if (method.getParameterTypes().length != 0) {
            return false;
        }

        // check method name
        if (!("action".equals(method.getName()))) {
            return false;
        }

        // check return type
        if (method.getReturnType() != void.class) {
            return false;
        }

        // check parameter type is correct
        // if (method.getParameterTypes()[0] != String.class) {
        //     return false;
        // }

        return true;
    }

}
