package com.fantasy.main;

import com.fantasy.impls.pool.T1000Pool;
import com.fantasy.impls.robot.ModelT1000;
import com.fantasy.interfaces.Robot;
import com.fantasy.interfaces.RobotConveyor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by Djelu on 31.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

        T1000Pool t1000Pool = (T1000Pool) context.getBean("t1000Pool");
        t1000Pool.action();
    }
}
