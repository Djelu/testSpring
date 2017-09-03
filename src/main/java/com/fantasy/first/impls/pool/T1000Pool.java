package com.fantasy.first.impls.pool;

import com.fantasy.first.interfaces.Robot;
import com.fantasy.first.interfaces.RobotPool;

import java.util.Collection;

/**
 * Created by Djelu on 01.09.2017.
 */
public class T1000Pool implements RobotPool {

/*    private  Map<String, Robot> robotCollection;

    public T1000Pool(Map<String, Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    @Override
    public Map<String, Robot> getRobotCollection() {
        return robotCollection;
    }

    public void setRobotCollection( Map<String, Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    public void action() {
        for(Map.Entry<String, Robot> entry: robotCollection.entrySet()){
            System.out.println("key: " + entry.getKey());
            System.out.println("value: ");
            Robot robot = entry.getValue();
            robot.action();
            robot.printPars();
        }
    }*/
    private Collection<Robot> robotCollection;

    public T1000Pool(Collection<Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    @Override
    public Collection<Robot> getRobotCollection() {
        return robotCollection;
    }

    public void setRobotCollection( Collection<Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    public void action() {
        for(Robot robot: robotCollection){
            robot.action();
            robot.printPars();
        }
    }
}
