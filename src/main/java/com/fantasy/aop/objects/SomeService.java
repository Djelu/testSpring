package com.fantasy.aop.objects;

import org.springframework.stereotype.Component;

/**
 * Created by Djelu on 02.09.2017.
 */
@Component
public class SomeService {

    public int getIntValue(){
        return 5;
    }

    public double getDoubleValue(){
        return 5.6;
    }

    public double div(int a, int b){
        return a/b;
    }
}
