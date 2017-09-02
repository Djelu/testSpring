package com.fantasy.main;

import com.fantasy.aop.objects.CustomFileFilter;
import com.fantasy.aop.objects.FileManager;
import com.fantasy.aop.objects.SomeService;
import com.fantasy.impls.robot.ModelT1000;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Djelu on 31.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("AOPContext.xml");

        FileManager fileManager = (FileManager) context.getBean("fileManager");

        fileManager.getExtensionCount("c:\\Windows\\System32");
        fileManager.getExtensionList("c:\\Windows\\");
    }
}
