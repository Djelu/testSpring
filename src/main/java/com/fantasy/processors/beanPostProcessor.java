package com.fantasy.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Djelu on 31.08.2017.
 */
@Configuration
public class beanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object obj, String name) throws BeansException {
        return obj;
    }

    public Object postProcessAfterInitialization(Object obj, String name) throws BeansException {
        System.err.println(obj + " - postProcessBeforeInitialization()");
        return obj;
    }
}
